//64050400 ญาณิศา ศิริมัญจา
//1.6 o.getValue() ได้เท่ากับ 3

import java.util.ArrayList;
import java.util.Arrays;
public class Lab_MatrixMul {
    public static void main(String[] args) {
    int[][] inputA = { { 5, 6, 7 }, { 4, 8, 9 } };
    int[][] inputB = { { 6, 4 }, { 5, 7 }, { 1, 1 } };
    MyData matA = new MyData(inputA);
    MyData matB = new MyData(inputB);
    int matC_r = matA.data.length;
    int matC_c = matB.data[0].length;
    MyData matC = new MyData(matC_r, matC_c);
    
    //Q4
    ArrayList<Thread> t_List = new ArrayList<Thread>();
    for(int i = 0; i < matC_r; i ++){
        MatrixMulThread task = new MatrixMulThread(i, i, matA, matB, matC);
        Thread thread = new Thread(task);
        thread.start();
        t_List.add(thread);    
    }
        
    //Q5
    for(Thread t : t_List){
        try{
            t.join();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    matC.show();
    }
}
class MatrixMulThread implements Runnable {
    int processing_row; int processing_col;
    MyData datA; MyData datB; MyData datC;
    MatrixMulThread(int tRow, int tCol,
            MyData a, MyData b, MyData c) {
    
    // Q1 
        this.processing_row = tRow;
        this.processing_col = tCol;
        this.datA = a;
        this.datB = b;
        this.datC = c;
        
    }
    /* Q2 */ public void run() {
    
    // Q3
    for(int i = 0; i < datB.data[0].length; i++){
        datC.data[processing_row][i] = 0;
        for(int j = 0; j < datA.data[processing_row].length;j++){
            datC.data[processing_row][i] += datA.data[processing_row][j] * datB.data[j][i];
        }
    }
    // System.out.println("perform sum of multiplication of assigned row and col");
    }
} //class

class MyData {
    int[][] data;
    MyData(int[][] m) { data = m; }
    MyData(int r, int c) {
    data = new int[r][c];
    for (int[] aRow : data)
    Arrays.fill(aRow, 9);
    // 9 will be overwritten anyway
    }
    void show() {
        System.out.println(Arrays.deepToString(data));
    }
} //class