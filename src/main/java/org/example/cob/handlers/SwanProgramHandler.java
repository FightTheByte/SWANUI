package org.example.cob.handlers;
import com.google.common.eventbus.Subscribe;
import org.example.cob.customevents.CallSwanEvent;
import org.example.cob.customevents.ReturnSwanResultEvent;
import org.example.cob.eventbus.SwanEventBus;
import org.example.cob.swan.SwanAdapter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public final class SwanProgramHandler{
    String swanResult;
    SwanAdapter swanAdapter;
    private static SwanProgramHandler INSTANCE;
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    private SwanProgramHandler(boolean testing){
        swanAdapter = new SwanAdapter(testing);
    }

    public synchronized static SwanProgramHandler getInstance(boolean testing){
        if(INSTANCE == null){
            INSTANCE = new SwanProgramHandler(testing);
        }
        return INSTANCE;
    }

    @Subscribe
    public void runSwan(CallSwanEvent callSwanEvent) throws ExecutionException, InterruptedException {
        Future<String> future = executor.submit(() -> {
            String os = System.getProperty("os.name");
            if(os.startsWith("Windows")){
                swanResult = swanAdapter.runSwanWithArgumentsWindows();
            }
            else if(os.startsWith("Mac")){
                swanResult = swanAdapter.runSwanWithArgumentsMac();
            }
            else{
                swanResult = swanAdapter.runSwanWithArgumentsLinux();
            }

            return swanResult;
        });

        String result = future.get();
        swanResult(result);
        executor.shutdown();
    }

    private void swanResult(String result){
        SwanEventBus.returnEventBus().post(new ReturnSwanResultEvent(result));
    }
}
