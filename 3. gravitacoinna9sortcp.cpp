#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

/**
 * Алгоритм имитирует падение бусин под действием гравитации
 */

/**
 * Основной метод сортировки бусинами
 * param arr - вектор неотрицательных целых чисел
 */
void beadSort(vector<int>& arr) {
    if (arr.empty()) {
        return;
    }
    
    // Находим максимальное значение
    int maxVal = *max_element(arr.begin(), arr.end());
    
    // Создаем матрицу бусин
    vector<vector<bool>> beads(arr.size(), vector<bool>(maxVal, false));
    
    // Размещаем бусины в начальной позиции
    for (int i = 0; i < arr.size(); i++) {
        for (int j = 0; j < arr[i]; j++) {
            beads[i][j] = true;
        }
    }
    
    // Процесс "падения" бусин под действием гравитации
    for (int j = 0; j < maxVal; j++) {
        int sum = 0;
        
        // Подсчитываем бусины в текущей строке
        for (int i = 0; i < arr.size(); i++) {
            if (beads[i][j]) {
                sum++;
                beads[i][j] = false;
            }
        }
        
        // Бусины падают вниз - размещаем их в нижних позициях
        for (int i = arr.size() - 1; i >= arr.size() - sum; i--) {
            beads[i][j] = true;
        }
    }
    
    // Преобразуем матрицу бусин обратно в числа
    for (int i = 0; i < arr.size(); i++) {
        int count = 0;
        for (int j = 0; j < maxVal; j++) {
            if (beads[i][j]) {
                count++;
            }
        }
        arr[i] = count;
    }
}

/**
 * Оптимизированная версия без использования матрицы
 */
void beadSortOptimized(vector<int>& arr) {
    if (arr.empty()) {
        return;
    }
    
    int maxVal = *max_element(arr.begin(), arr.end());
    vector<int> beadCounts(maxVal, 0);
    
    // Распределение бусин по уровням
    for (int i = 0; i < arr.size(); i++) {
        for (int j = 0; j < arr[i]; j++) {
            beadCounts[j]++;
        }
    }
    
    // Сборка отсортированного массива
    for (int i = 0; i < arr.size(); i++) {
        int count = 0;
        for (int j = 0; j < maxVal; j++) {
            if (beadCounts[j] > arr.size() - 1 - i) {
                count++;
            }
        }
        arr[i] = count;
    }
}

/**
 * Визуализация матрицы бусин
 */
void printBeads(const vector<vector<bool>>& beads) {
    if (beads.empty() || beads[0].empty()) return;
    
    for (int j = beads[0].size() - 1; j >= 0; j--) {
        for (int i = 0; i < beads.size(); i++) {
            cout << (beads[i][j] ? "○ " : "· ");
        }
        cout << endl;
    }
    cout << endl;
}

/**
 * Вспомогательная функция для вывода вектора
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
    cout << "=== Сортировка бусинами на C++ ===" << endl;
    
    vector<int> arr1 = {3, 1, 4, 1, 5, 2};
    cout << "Исходный массив:" << endl;
    printVector(arr1);
    
    beadSort(arr1);
    
    cout << "Отсортированный массив:" << endl;
    printVector(arr1);
    
    // Демонстрация с визуализацией
    cout << "\nДемонстрация с визуализацией:" << endl;
    vector<int> arr2 = {2, 4, 1, 3};
    cout << "Исходный массив: ";
    printVector(arr2);
    
    // Визуализация начального состояния
    int maxVal = 4;
    vector<vector<bool>> beads(arr2.size(), vector<bool>(maxVal, false));
    for (int i = 0; i < arr2.size(); i++) {
        for (int j = 0; j < arr2[i]; j++) {
            beads[i][j] = true;
        }
    }
    
    cout << "Начальное расположение бусин:" << endl;
    printBeads(beads);
    
    beadSort(arr2);
    cout << "Отсортированный массив: ";
    printVector(arr2);
    
    // Тестирование оптимизированной версии
    vector<int> arr3 = {5, 3, 8, 1, 2};
    cout << "\nОптимизированная версия:" << endl;
    cout << "Исходный массив: ";
    printVector(arr3);
    
    beadSortOptimized(arr3);
    
    cout << "Отсортированный массив: ";
    printVector(arr3);
    
    return 0;
}
