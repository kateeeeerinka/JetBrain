class ArrayOperations {
    public static void printCorners(int[][] array) {
       
        int numRows = array.length;
        int numCols = array[0].length;

        // Print top-left corner
        System.out.print(array[0][0] + " ");

        // Print top-right corner
        System.out.println(array[0][numCols - 1]);

        // Print bottom-left corner
        System.out.print(array[numRows - 1][0] + " ");

        // Print bottom-right corner
        System.out.println(array[numRows - 1][numCols - 1]);
    }
}






