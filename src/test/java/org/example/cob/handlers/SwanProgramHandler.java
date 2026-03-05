package org.example.cob.handlers;
import org.example.cob.customevents.ReturnSwanResultEvent;
import org.example.cob.eventbus.SwanEventBus;
import org.example.cob.swan.SwanAdapter;
import org.example.cob.swan.SwanAdapterTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public final class SwanProgramHandler{
    String swanResult;
    SwanAdapter swanAdapter;
    private static SwanProgramHandler INSTANCE;
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    private SwanProgramHandler(){
    }

    public synchronized static SwanProgramHandler getInstance(){
        if(INSTANCE == null){
            INSTANCE = new SwanProgramHandler();
        }
        return INSTANCE;
    }

    public void runSwan() {

        executor.submit(() -> {
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

            swanResult(swanResult);
        });
    }

    private void swanResult(String result){
        SwanEventBus.returnEventBus().post(new ReturnSwanResultEvent(result));
    }
}
