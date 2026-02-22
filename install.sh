#!/bin/sh

swan_bin_output_dir=${SWAN_DIR:-/tmp/swan}
swan_bin_output_gz="swan.tar.gz"
swan_bin_url="https://swanmodel.sourceforge.io/download/zip/SWAN-VERSION-PLATFORM.tar.gz"
version="41.51"

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
# Ensure uname installed -- seems to be best way to check OS
command -v uname >/dev/null 2>&1 ||
    echo "Could not determine platform. 'uname' is not available." || exit 1

platform="$(uname)"
# Handle when not MacOS or Linux -- no binary available for other systems
[ $platform != "Linux" ] && [ $platform != "MacOS" ] &&
    echo "SWAN binaries are only available for Windows, MacOS and Linux." && exit 1

swan_bin_url=${swan_bin_url//PLATFORM/$platform} # Substitute platform into URL
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
command -v tar >/dev/null 2>&1 ||
    echo "'tar' command is not available to extract archive." || exit 1
printf 'Extracting archive to %s' "$swan_bin_output_dir"
progress_dots
tar -zxf $swan_bin_output_dir/$swan_bin_output_gz -C $swan_bin_output_dir
end_progress_dots
echo "Extraction Complete."

# Remove archive
rm $swan_bin_output_dir/$swan_bin_output_gz

# Copy binary to ~/.local/bin
echo "Copying binaries to ~/.local/bin"
[ -d ~/.local/bin ] || mkdir -p ~/.local/bin
cp $swan_bin_output_dir/SWAN-$version-$platform/bin/* ~/.local/bin

# Add to PATH if needed
if [ $(expr match $PATH ~/.ocal/bin) -eq 0 ]; then
    echo "Adding ~/.local/bin to PATH in profile"
    [ -f ~/.profile ] && echo "export PATH=$PATH:$HOME/.local/bin" >> $HOME/.profile &&
        echo "Updated PATH in ~/.profile"
    [ -f ~/.bash_profile ] && echo "export PATH=$PATH:$HOME/.local/bin" >> $HOME/.bash_profile &&
        echo "Updated PATH in ~/.bash_profile"
    [ -f ~/.zprofile ] && echo "export PATH=$PATH:$HOME/.local/bin" >> $HOME/.zprofile &&
        echo "Updated PATH in ~/.zprofile"
fi

# Possible libimf section here

# Cleanup
echo "Cleaning up"
rm -r $swan_bin_output_dir

