/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threesum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author TofixXx
 */
public class ThreeSum {

    /**
     * @param args the command line arguments
     */
    static int[] TSum(int arr[], int N) {
        int SortedArr[] = arr.clone();
        Arrays.sort(SortedArr);
        int res[] = new int[3];
        res[0] = Integer.MAX_VALUE;
        for (int k = 0; k < N; k++) {
            int ij[] = TwoSum(SortedArr, N, SortedArr[k], k);
            if (ij[0] != -1) {
                res[0] = SortedArr[k];
                res[1] = SortedArr[ij[0]];
                res[2] = SortedArr[ij[1]];
            }
        }
        return SearchInNotSorted(arr, N, res);
    }

    static int[] TwoSum(int arr[], int N, int El, int ElInd) {
        int inds[] = new int[2];
        inds[0] = -1;
        int i = 0, j = N - 1;
        if (j == ElInd) {
            j--;
        } else if (i == ElInd) {
            i++;
        }
        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum > -El) {
                j--;
            } else if (sum < -El) {
                i++;
                if (i == ElInd) {
                    i++;
                }
            } else {
                inds[0] = i;
                inds[1] = j;
                j--;
            }
            if (j == ElInd) {
                j--;
            } else if (i == ElInd) {
                i++;
            }
        }
        return inds;
    }

    static int[] SearchInNotSorted(int arr[], int N, int[] elems) {
        int a = -1, b = -1, c = -1;
        int res[] = new int[3];
        res[0] = -1;
        if (elems[0] == Integer.MAX_VALUE) {
            return res;
        }
        for (int i = 0; i < N; i++) {
            if (arr[i] == elems[0]) {
                a = i;
                break;
            }
        }
        for (int i = 0; i < N; i++) {
            if (arr[i] == elems[1]) {
                b = i;
                break;
            }
        }
        for (int i = 0; i < N; i++) {
            if (arr[i] == elems[2]) {
                c = i;
                break;
            }
        }
        if (a > b) {
            if (b > c) {
                res[2] = a + 1;
                res[1] = b + 1;
                res[0] = c + 1;
            } else {
                if (a < c) {
                    res[2] = c + 1;
                    res[1] = a + 1;
                } else {
                    res[2] = a + 1;
                    res[1] = c + 1;
                }
                res[0] = b + 1;
            }

        } else {
            if (a > c) {
                res[2] = b + 1;
                res[1] = a + 1;
                res[0] = c + 1;
            } else {
                if (b < c) {
                    res[2] = c + 1;
                    res[1] = b + 1;
                } else {
                    res[2] = b + 1;
                    res[1] = c + 1;
                }
                res[0] = a + 1;
            }
        }
        return res;
    }

    public static void main(String[] args){
        // TODO code application logic here
        try (BufferedReader reader = new BufferedReader(new FileReader("rosalind_3sum.txt"));
                FileWriter writer = new FileWriter(new File("output.txt"))) {

            String inp[] = reader.readLine().split(" ");
            int k = Integer.parseInt(inp[0]);
            int n = Integer.parseInt(inp[1]);
            while (reader.ready()) {
                int arr[] = new int[n];
                String str[] = reader.readLine().split(" ");
                for (int i = 0; i < n; i++) {
                    arr[i] = Integer.parseInt(str[i]);
                }
                int res[] = TSum(arr, n);
                if (res[0] == -1) {
                    writer.write(Integer.toString(res[0]));
                    writer.flush();
                    writer.write(System.getProperty("line.separator"));
                    writer.flush();
                } else {
                    writer.write(Integer.toString(res[0]) + " " + Integer.toString(res[1]) + " " + Integer.toString(res[2]));
                    writer.flush();
                    writer.write(System.getProperty("line.separator"));
                    writer.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
