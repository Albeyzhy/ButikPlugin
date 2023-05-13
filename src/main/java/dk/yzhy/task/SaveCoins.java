package dk.yzhy.task;

import dk.yzhy.Butik;

public class SaveCoins implements Runnable {
    @Override
    public void run() {
        Butik.SaveToYAML();
    }
}
