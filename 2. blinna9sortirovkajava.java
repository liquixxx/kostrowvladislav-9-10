public class PancakeSort {
    
    /**
     * Блинная сортировка - алгоритм сортировки, который использует
     * операцию переворота элементов массива до определенного индекса
     */
    
    /**
     * Основной метод блинной сортировки
     * @param arr - массив для сортировки
     */
    public static void pancakeSort(int[] arr) {
        // Начинаем с конца массива и постепенно уменьшаем размер несортированной части
        for (int currentSize = arr.length; currentSize > 1; currentSize--) {
            // Находим индекс максимального элемента в несортированной части
            int maxIndex = findMaxIndex(arr, currentSize);
            
            // Если максимальный элемент не на своем месте, выполняем перевороты
            if (maxIndex != currentSize - 1) {
                // Переворачиваем так, чтобы максимальный элемент оказался в начале
                if (maxIndex != 0) {
                    flip(arr, maxIndex);
                }
                // Переворачиваем так, чтобы максимальный элемент оказался на своем месте
                flip(arr, currentSize - 1);
            }
        }
    }
    
    /**
     * Находит индекс максимального элемента в подмассиве [0...size-1]
     * @param arr - массив
     * @param size - размер подмассива для поиска
     * @return индекс максимального элемента
     */
    private static int findMaxIndex(int[] arr, int size) {
        int maxIndex = 0;
        for (int i = 1; i < size; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    /**
     * Переворачивает элементы массива от начала до указанного индекса
     * Пример: flip([1,2,3,4,5], 2) -> [3,2,1,4,5]
     * @param arr - массив
     * @param index - индекс до которого переворачиваем
     */
    private static void flip(int[] arr, int index) {
        int start = 0;
        while (start < index) {
            // Меняем местами элементы
            int temp = arr[start];
            arr[start] = arr[index];
            arr[index] = temp;
            start++;
            index--;
        }
    }
    
    /**
     * Вспомогательный метод для вывода массива
     */
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    /**
     * Демонстрация работы алгоритма
     */
    public static void main(String[] args) {
        int[] arr1 = {23, 10, 20, 11, 12, 6, 7};
        System.out.println("Исходный массив:");
        printArray(arr1);
        
        pancakeSort(arr1);
        
        System.out.println("Отсортированный массив:");
        printArray(arr1);
        
        // Дополнительный пример
        int[] arr2 = {3, 1, 4, 1, 5, 9, 2, 6};
        System.out.println("\nВторой пример:");
        System.out.println("Исходный массив:");
        printArray(arr2);
        
        pancakeSort(arr2);
        
        System.out.println("Отсортированный массив:");
        printArray(arr2);
        
        // Демонстрация работы flip
        int[] demo = {1, 2, 3, 4, 5};
        System.out.println("\nДемонстрация переворота:");
        System.out.println("До flip(arr, 2):");
        printArray(demo);
        flip(demo, 2);
        System.out.println("После flip(arr, 2):");
        printArray(demo);
    }
}
