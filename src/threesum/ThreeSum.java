
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

    private static int[] findThreeElementsWithZeroSum(int arr[]) {
        int[] sortedArr = new int[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);
        Arrays.sort(sortedArr);
        int[] resValues = new int[3];
        resValues[0] = Integer.MAX_VALUE;
        for (int k = 0; k < arr.length; k++) {
            int ij[] = findTwoElementsBySum(sortedArr, k);
            if (ij[0] != -1) {
                resValues[0] = sortedArr[k];
                resValues[1] = sortedArr[ij[0]];
                resValues[2] = sortedArr[ij[1]];
            }
        }
        return resValues[0] != Integer.MAX_VALUE ? 
                getIndexesByValues(arr, resValues) : new int[]{-1};
    }

    /**
     * @param arr sorted array
     * @param sumResElemIndex index of element that should be sum of two other
     * @return indexes of resut two elements
     */
    private static int[] findTwoElementsBySum(int arr[], int sumResElemIndex) {
        int inds[] = new int[2];
        inds[0] = -1;
        int i = 0, j = arr.length - 1;
        if (j == sumResElemIndex) {
            j--;
        } else if (i == sumResElemIndex) {
            i++;
        }
        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum > -arr[sumResElemIndex]) {
                j--;
            } else if (sum < -arr[sumResElemIndex]) {
                i++;
                if (i == sumResElemIndex) {
                    i++;
                }
            } else {
                inds[0] = i;
                inds[1] = j;
                j--;
            }
            if (j == sumResElemIndex) {
                j--;
            } else if (i == sumResElemIndex) {
                i++;
            }
        }
        return inds;
    }

    /** 
     * @param arr int array
     * @param elems values of elements
     * @return sorted indexes of elements
     */
    private static int[] getIndexesByValues(int arr[], int[] elems) {
        
        int res[] = new int[elems.length];
        Arrays.fill(res, -1);
        
        for (int i = 0; i < arr.length; i++) {
            for(int j = 0; j < res.length; j++){
                if (arr[i] == elems[j] && res[j] == -1) {
                    res[j] = i+1;
                    break;
                }
            }
        }
        
        Arrays.sort(res);
        return res;
    }

    public static void main(String[] args){
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
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
                int res[] = findThreeElementsWithZeroSum(arr);
                for(int i = 0; i < res.length; i++){
                    writer.write(Integer.toString(res[i]));
                    if(i == res.length - 1){
                        writer.write(System.getProperty("line.separator"));
                    }
                    else{
                        writer.write(" ");
                    }
                    writer.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
