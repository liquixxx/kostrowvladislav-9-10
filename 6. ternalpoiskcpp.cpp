#include <iostream>
#include <vector>
#include <algorithm>

// Итеративный тернарный поиск в отсортированном массиве
int ternarySearch(const std::vector<int>& arr, int key) {
    int left = 0;
    int right = arr.size() - 1;

    while (left <= right) {
        // Находим два промежуточных индекса, разделяющих массив на три части
        int mid1 = left + (right - left) / 3;
        int mid2 = right - (right - left) / 3;

        // Проверяем, совпадает ли ключ с одним из промежуточных индексов
        if (arr[mid1] == key) {
            return mid1;
        }
        if (arr[mid2] == key) {
            return mid2;
        }

        // Определяем, в какой из трех частей находится ключ, и сужаем диапазон поиска
        if (key < arr[mid1]) {
            right = mid1 - 1; // Ищем в первой части (слева)
        } else if (key > arr[mid2]) {
            left = mid2 + 1; // Ищем во второй части (справа)
        } else {
            left = mid1 + 1; // Ищем в средней части
            right = mid2 - 1;
        }
    }

    // Если элемент не найден, возвращаем -1
    return -1;
}

int main() {
    // Пример использования
    std::vector<int> sortedArray = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
    int keyToFind = 23;

    int index = ternarySearch(sortedArray, keyToFind);

    if (index != -1) {
        std::cout << "Элемент " << keyToFind << " найден по индексу: " << index << std::endl;
    } else {
        std::cout << "Элемент " << keyToFind << " не найден." << std::endl;
    }

    keyToFind = 100;
    index = ternarySearch(sortedArray, keyToFind);

    if (index != -1) {
        std::cout << "Элемент " << keyToFind << " найден по индексу: " << index << std::endl;
    } else {
        std::cout << "Элемент " << keyToFind << " не найден." << std::endl;
    }

    return 0;
}
