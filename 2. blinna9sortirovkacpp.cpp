#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
void flip(vector<int>& arr, int index) {
    int start = 0;
    while (start < index) {
        // Используем swap из STL для обмена элементов
        swap(arr[start], arr[index]);
        start++;
        index--;
    }
}

/**
 * Находит индекс максимального элемента в подмассиве [0...size-1]
 * param arr - вектор
 * param size - размер подмассива для поиска
 * return индекс максимального элемента
 */
int findMaxIndex(const vector<int>& arr, int size) {
    int maxIndex = 0;
    for (int i = 1; i < size; i++) {
        if (arr[i] > arr[maxIndex]) {
            maxIndex = i;
        }
    }
    return maxIndex;
}

/**
 * Основной метод блинной сортировки
 * @param arr - вектор для сортировки
 */
void pancakeSort(vector<int>& arr) {
    // Начинаем с конца вектора и постепенно уменьшаем размер несортированной части
    for (int currentSize = arr.size(); currentSize > 1; currentSize--) {
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
 * Вспомогательный метод для вывода вектора
 */
void printVector(const vector<int>& arr) {
    for (int num : arr) {
        cout << num << " ";
    }
    cout << endl;
}

/**
 * Демонстрация работы алгоритма
 */
int main() {
    cout << "=== Блинная сортировка на C++ ===" << endl;
    
    vector<int> arr1 = {23, 10, 20, 11, 12, 6, 7};
    cout << "Исходный массив:" << endl;
    printVector(arr1);
    
    pancakeSort(arr1);
    
    cout << "Отсортированный массив:" << endl;
    printVector(arr1);
    
    // Дополнительный пример
    vector<int> arr2 = {3, 1, 4, 1, 5, 9, 2, 6};
    cout << "\nВторой пример:" << endl;
    cout << "Исходный массив: ";
    printVector(arr2);
    
    pancakeSort(arr2);
    
    cout << "Отсортированный массив: ";
    printVector(arr2);
    
    // Демонстрация работы flip
    vector<int> demo = {1, 2, 3, 4, 5};
    cout << "\nДемонстрация переворота:" << endl;
    cout << "До flip(arr, 2): ";
    printVector(demo);
    flip(demo, 2);
    cout << "После flip(arr, 2): ";
    printVector(demo);
    
    return 0;
}
