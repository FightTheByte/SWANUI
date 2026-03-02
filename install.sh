#!/bin/sh

swan_bin_output_dir="/tmp/swan"
swan_bin_output_gz="swan.tar.gz"
swan_bin_url="https://swanmodel.sourceforge.io/download/zip/SWAN-VERSION-PLATFORM.tar.gz"
version="41.51"

run_cob() { # TODO
    echo "Future versions of this script will automatically launch COB. Exiting."
    exit 0
}

# If SWAN binary detected, skip install and run COB
if command -v swanrun >/dev/null 2>&1; then
    echo "SWAN detected. Starting COB."
    run_cob
    exit 0
fi

# Ensure uname installed -- seems to be best way to check OS
command -v uname >/dev/null 2>&1 ||
    echo "Could not determine platform. 'uname' is not available." || exit 1

platform=$(uname)
arch=$(uname -m)

# Handle when not MacOS or Linux -- no binary available for other systems
[ $platform != "Linux" ] && [ $platform != "Darwin" ] &&
    echo "SWAN binaries are only available for Windows, MacOS and Linux." && exit 1

# Ensure tar installed -- required to extract archives
command -v tar >/dev/null 2>&1 ||
    echo "'tar' command is not available to extract archive." || exit 1

# Progression dots functions
progress_dots() {
    (
        while true; do
            i=0
            while [ $i -lt 3 ]; do
                printf '.'
                sleep 1
                i=$(( i + 1 ))
            done
            i=0
            printf '\b\b\b%3s\b\b\b' " " # Overwrite the 3 dots
            sleep 1
        done
        ) &
}

end_progress_dots() {
    { printf '\n'; kill $! && wait $!; } 2>/dev/null
}

contains_substring() {
    string="$1"
    substring="$2"

    case "$string" in
        *"$substring"*) return 1 ;;
        *) return 0 ;;
    esac
}

http_download() {
    url="$1"
    output="$2"

    if command -v curl >/dev/null 2>&1; then
        printf "Downloading"
        progress_dots
        curl -fsSL -o "$output" "$url"
        # curl -fSL -o "$output" "$url" # Debug option
        download_exit_code=$?
        end_progress_dots
    elif command -v wget >/dev/null 2>&1; then
        printf "Downloading"
        progress_dots
        wget -q --retry-connrefused --waitretry=1 --read-timeout=20 -O "$output" "$url"
        # wget --retry-connrefused --waitretry=1 --read-timeout=20 -O "$output" "$url" # Debug option
        download_exit_code=$?
        end_progress_dots
    elif command -v fetch >/dev/null 2>&1; then
        printf "Downloading"
        progress_dots
        fetch -q -o "$output" "$url"
        # fetch -o "$output" "$url" # Debug option
        download_exit_code=$?
        end_progress_dots
    else
        echo "Error: No HTTP client (curl, wget, fetch) found."
        return 1
    fi
    return $download_exit_code
}

# Configure Download URL
if [ $platform = "Linux" ]; then
    swan_bin_url=${swan_bin_url//PLATFORM/Linux}
elif [ $platform = "Darwin" ] && [ $arch = "arm64" ]; then
    swan_bin_url=${swan_bin_url//PLATFORM/macOS\-Silicon}
elif [ $platform = "Darwin"]; then
    swan_bin_url=${swan_bin_url//PLATFORM/macOS}
fi
swan_bin_url=${swan_bin_url//VERSION/$version} # Substitute version into URL

# Ensure output directory exists
[ -d $swan_bin_output_dir ] || mkdir $swan_bin_output_dir || exit 1

# Download SWAN
if http_download "$swan_bin_url" "$swan_bin_output_dir/$swan_bin_output_gz"; then
    echo "Download Complete."
else
    echo "Download Failed." >&2
    exit 1
fi

# Extract SWAN archive
printf 'Extracting archive to %s' "$swan_bin_output_dir"
progress_dots
tar -zxf $swan_bin_output_dir/$swan_bin_output_gz -C $swan_bin_output_dir
end_progress_dots
echo "Extraction Complete."

# Copy binary to ~/.local/bin
echo "Copying binaries to ~/.local/bin"
[ -d ~/.local/bin ] || mkdir -p ~/.local/bin
cp $swan_bin_output_dir/SWAN-$version-*/bin/* ~/.local/bin

# Add to PATH if needed
if contains_substring $PATH ~/.local/bin; then
    echo "Adding ~/.local/bin to PATH in profile"
    [ -f ~/.profile ] && echo "export PATH=$PATH:$HOME/.local/bin" >> $HOME/.profile &&
        echo "Updated PATH in ~/.profile" && . $HOME/.profile
    contains_substring $SHELL "zsh" && echo "export PATH=$PATH:$HOME/.local/bin" >> $HOME/.bash_profile &&
        echo "Updated PATH in ~/.bash_profile" && . $HOME/.bash_profile
    contains_substring $SHELL "bash" && echo "export PATH=$PATH:$HOME/.local/bin" >> $HOME/.zprofile &&
        echo "Updated PATH in ~/.zprofile" && . $HOME/.zprofile

    if [ ! -f ~/.profile ] && ! contains_substring $SHELL "bash" && ! contains_substring $SHELL "zsh"; then
        echo "export PATH=$PATH:$HOME/.local/bin" >> $HOME/.profile &&
            echo "Updated PATH in ~/.profile"
    fi
    echo "Make sure to source any changed files. For example: '. ~/.profile'"
fi

# Cleanup
echo "Cleaning up"
rm -r $swan_bin_output_dir

run_cob
