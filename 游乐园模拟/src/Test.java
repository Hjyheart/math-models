import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by hongjiayong on 16/4/29.
 */
public class Test {
    private static FileWriter writer;
    Test(FileWriter writer){
        this.writer = writer;
    }

    public static void main(String [] args) throws IOException {
        youleyuan.pointAWriter = new FileWriter("/Users/hongjiayong/IdeaProjects/游乐园模拟/src/A.txt");
//        youPoint A = new youPoint(1, youleyuan.pointAWriter, 3, 10);
//        A.go();
    }
}
