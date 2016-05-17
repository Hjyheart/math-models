import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hongjiayong on 16/4/29.
 */
public class youleyuan {
    public static int yuan[][]={
            {10000, 300, 400, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000},
            {300, 10000, 300, 10000, 10000, 350, 10000, 10000, 10000, 10000, 250},
            {400, 300, 10000, 300, 10000, 350, 10000, 10000, 10000, 10000, 10000},
            {10000, 10000, 300, 10000, 450, 500, 10000, 10000, 10000, 10000, 10000},
            {10000, 10000, 10000, 450, 10000, 10000, 500, 10000, 10000, 10000, 10000},
            {10000, 350, 350, 500, 10000, 10000, 10000, 550, 10000, 450, 10000},
            {10000, 10000, 10000, 10000, 500, 10000, 10000, 650, 10000, 10000, 10000},
            {10000, 10000, 10000, 10000, 10000, 550, 650, 10000, 400, 10000, 10000},
            {10000, 10000, 10000, 10000, 10000, 10000, 10000, 400, 10000, 450, 10000},
            {10000, 10000, 10000, 10000, 10000, 450, 10000, 10000, 450, 10000, 350},
            {10000, 250, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 350, 10000}
    };

    public static int minRoad[][]={
            {10000, 300, 400, 700, 1150, 650, 1650, 1200, 1350, 900, 550},
            {300, 10000, 300, 600, 1050, 350, 1550, 900, 1050, 600, 250},
            {400, 300, 10000, 300, 750, 350, 1250, 900, 1250, 800, 550},
            {700, 600, 300, 10000, 450, 500, 950, 1050, 1400, 950, 850},
            {1150, 1050, 750, 450, 10000, 950, 500, 1150, 1550, 1400, 1300},
            {650, 350, 350, 500, 950, 10000, 1200, 550, 900, 450, 600},
            {1650, 1550, 1250, 950, 500, 1200, 10000, 650, 1050, 1500, 1800},
            {1200, 900, 900, 1050, 1150, 550, 650, 10000, 400, 850, 1150},
            {1350, 1050, 1250, 1400, 1550, 900, 1050, 400, 10000, 450, 800},
            {900, 600, 800, 950, 1400, 450, 1500, 850, 450, 10000, 350},
            {550, 250, 550, 850, 1300, 600, 1800, 1150, 800, 350, 10000}
    };

    public static int staticTime;
    public static int youkeNum = 0;

    public static youPoint O;
    public static youPoint A;
    public static youPoint B;
    public static youPoint C;
    public static youPoint D;
    public static youPoint E;
    public static youPoint F;
    public static youPoint G;
    public static youPoint H;
    public static youPoint I;
    public static youPoint J;

    public static FileWriter pointOWriter;
    public static FileWriter pointAWriter;
    public static FileWriter pointBWriter;
    public static FileWriter pointCWriter;
    public static FileWriter pointDWriter;
    public static FileWriter pointEWriter;
    public static FileWriter pointFWriter;
    public static FileWriter pointGWriter;
    public static FileWriter pointHWriter;
    public static FileWriter pointIWriter;
    public static FileWriter pointJWriter;
    public static ArrayList<youPoint> points = new ArrayList<youPoint>();

    public static FileWriter visitorTimeLog;


    public static void main(String[] args) throws IOException, InterruptedException {

        staticTime = 0;

        pointAWriter = new FileWriter("/Users/hongjiayong/IdeaProjects/游乐园模拟/src/A.txt");
        pointAWriter.write("A\n");
        pointBWriter = new FileWriter("/Users/hongjiayong/IdeaProjects/游乐园模拟/src/B.txt");
        pointBWriter.write("B\n");
        pointCWriter = new FileWriter("/Users/hongjiayong/IdeaProjects/游乐园模拟/src/C.txt");
        pointCWriter.write("C\n");
        pointDWriter = new FileWriter("/Users/hongjiayong/IdeaProjects/游乐园模拟/src/D.txt");
        pointDWriter.write("D\n");
        pointEWriter = new FileWriter("/Users/hongjiayong/IdeaProjects/游乐园模拟/src/E.txt");
        pointEWriter.write("E\n");
        pointFWriter = new FileWriter("/Users/hongjiayong/IdeaProjects/游乐园模拟/src/F.txt");
        pointFWriter.write("F\n");
        pointGWriter = new FileWriter("/Users/hongjiayong/IdeaProjects/游乐园模拟/src/G.txt");
        pointGWriter.write("G\n");
        pointHWriter = new FileWriter("/Users/hongjiayong/IdeaProjects/游乐园模拟/src/H.txt");
        pointHWriter.write("H\n");
        pointIWriter = new FileWriter("/Users/hongjiayong/IdeaProjects/游乐园模拟/src/I.txt");
        pointIWriter.write("I\n");
        pointJWriter = new FileWriter("/Users/hongjiayong/IdeaProjects/游乐园模拟/src/J.txt");
        pointJWriter.write("J\n");
        pointOWriter = new FileWriter("/Users/hongjiayong/IdeaProjects/游乐园模拟/src/O.txt");
        pointOWriter.write("O\n");

        visitorTimeLog = new FileWriter("/Users/hongjiayong/IdeaProjects/游乐园模拟/src/visitorTimeLog.txt");
        visitorTimeLog.write("游客入园出园时间统计\n");

        O = new youPoint(0, pointOWriter, 1, 10000, 10000);
        points.add(O);
        A = new youPoint(1, pointAWriter, 1980, 400, 400);
        points.add(A);
        B = new youPoint(2, pointBWriter, 75, 30, 150);
        points.add(B);
        C = new youPoint(3, pointCWriter, 150, 50, 300);
        points.add(C);
        D = new youPoint(4, pointDWriter, 150, 30, 100);
        points.add(D);
        E = new youPoint(5, pointEWriter, 300, 100, 300);
        points.add(E);
        F = new youPoint(6, pointFWriter, 150, 50, 200);
        points.add(F);
        G = new youPoint(7, pointGWriter, 120, 30, 100);
        points.add(G);
        H = new youPoint(8, pointHWriter, 90, 30, 200);
        points.add(H);
        I = new youPoint(9, pointIWriter, 90, 20, 100);
        points.add(I);
        J = new youPoint(10, pointJWriter, 120, 50, 200);
        points.add(J);

//        O = new youPoint(0, pointOWriter, 1, 10000);
//        points.add(O);
//        A = new youPoint(1, pointAWriter, 1980, 400);
//        points.add(A);
//        B = new youPoint(2, pointBWriter, 75, 30);
//        points.add(B);
//        C = new youPoint(3, pointCWriter, 150, 50);
//        points.add(C);
//        D = new youPoint(4, pointDWriter, 150, 30);
//        points.add(D);
//        E = new youPoint(5, pointEWriter, 300, 100);
//        points.add(E);
//        F = new youPoint(6, pointFWriter, 150, 50);
//        points.add(F);
//        G = new youPoint(7, pointGWriter, 120, 30);
//        points.add(G);
//        H = new youPoint(8, pointHWriter, 90, 30);
//        points.add(H);
//        I = new youPoint(9, pointIWriter, 90, 20);
//        points.add(I);
//        J = new youPoint(10, pointJWriter, 120, 50);
//        points.add(J);


        Queue<Integer> people = new LinkedList<Integer>();
        int count = 0;
        for (int i = 0; i < 10000; i++) {
            int dl = (int)(-3.88 * Math.log(Math.random()));
            count += dl;
            people.add(count);
        }

//        visitor vis = new visitor(staticTime, visitorTimeLog, count);
//        vis.addPlayed(0);
//        vis.setUnPlayedPoint(0);
//        vis.next();
//        points.get(0).getIn(vis);

        while(staticTime <= 46800){
            while(!people.isEmpty() && staticTime == people.peek()){
                visitor vis = new visitor(staticTime, visitorTimeLog);
                vis.addPlayed(0);
                vis.setUnPlayedPoint(0);
                vis.next();
                points.get(0).getIn(vis);
                count++;
                people.poll();
            }
            staticTime++;
            // A
            A.go();
            // B
            B.go();
            // C
            C.go();
            // D
            D.go();
            // E
            E.go();
            // F
            F.go();
            // G
            G.go();
            // H
            H.go();
            // I
            I.go();
            // J
            J.go();
        }
//        while (youkeNum < 10000);

//        Thread.sleep(2000);

        pointAWriter.close();
        pointBWriter.close();
        pointCWriter.close();
        pointDWriter.close();
        pointEWriter.close();
        pointFWriter.close();
        pointGWriter.close();
        pointHWriter.close();
        pointIWriter.close();
        pointJWriter.close();
        pointOWriter.close();

        visitorTimeLog.close();
    }
}
