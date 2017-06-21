package dankook.mse.hanbacklauncher;

/**
 * Created by 강남호 on 2017-06-12.
 */

interface MediaManagerInterface {
    void play(int id, int index);
    void stop(int index);
    void changeVolume(int a, int check[], int num);
}
