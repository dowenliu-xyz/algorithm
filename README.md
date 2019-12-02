# algorithm
《数据结构与算法之美》练习
## 数组
* 动态扩容数组
    * Java实现
        * [int动态扩容数组](./java/src/main/java/xyz/dowenliu/study/algo/_05_array/DynamicExpandIntArray.java)
        及[测试](./java/src/test/java/xyz/dowenliu/study/algo/_05_array/DynamicExpandIntArrayTest.java)
        * [泛型动态扩容数组](./java/src/main/java/xyz/dowenliu/study/algo/_05_array/DynamicExpandArray.java)
        及[测试](./java/src/test/java/xyz/dowenliu/study/algo/_05_array/DynamicExpandArrayTest.java)
* 大小固定数组
    * Java实现
        * [int大小固定数组](./java/src/main/java/xyz/dowenliu/study/algo/_05_array/SizeFixedIntArray.java)
        及[测试](./java/src/test/java/xyz/dowenliu/study/algo/_05_array/SizeFixedIntArrayTest.java)
        * [泛型动态扩容数组](./java/src/main/java/xyz/dowenliu/study/algo/_05_array/SizeFixedArray.java)
        及[测试](./java/src/test/java/xyz/dowenliu/study/algo/_05_array/SizeFixedArrayTest.java)
* 两个有序数组合并为一个有序数组
    * Java 实现
        * [有序数组合并](./java/src/main/java/xyz/dowenliu/study/algo/_05_array/IntSortedArrayMerging.java)
    * [LeetCode#88](https://leetcode-cn.com/problems/merge-sorted-array/)：
        * [Java提交1](https://leetcode-cn.com/submissions/detail/38200091/)  
        空间复杂度 O(1)；时间复杂度 最好 `O(m + n)` 最坏 `O(mn)`
        * [Java提交2](https://leetcode-cn.com/submissions/detail/38201053/)  
        空间复杂度 O(m)；时间复杂度 `O(m + n)`
## 链表
* 单链表
    * Java实现
        * [int单链表](./java/src/main/java/xyz/dowenliu/study/algo/_07_linked_list/IntSinglyLinkedList.java)
        及[测试](./java/src/test/java/xyz/dowenliu/study/algo/_07_linked_list/IntSinglyLinkedListTest.java)  
        实现了链表的反转
* 双向链表
    * Java实现
        * [int双向链表](./java/src/main/java/xyz/dowenliu/study/algo/_07_linked_list/IntDoublyLinkedList.java)
        及[测试](./java/src/test/java/xyz/dowenliu/study/algo/_07_linked_list/IntDoublyLinkedListTest.java)  
        实现了链表的反转
* 循环链表
    * Java实现
        * [int循环单链表](./java/src/main/java/xyz/dowenliu/study/algo/_07_linked_list/IntCircularSinglyLinkedList.java)
        及[测试](./java/src/test/java/xyz/dowenliu/study/algo/_07_linked_list/IntCircularSinglyLinkedListTest.java)  
        实现了链表的反转
        * [int循环双向链表](./java/src/main/java/xyz/dowenliu/study/algo/_07_linked_list/IntCircularDoublyLinkedList.java)
        及[测试](./java/src/test/java/xyz/dowenliu/study/algo/_07_linked_list/IntCircularDoublyLinkedListTest.java)  
        实现了链表的反转

* 判断是否回文链表
    * [LeetCode#234](https://leetcode-cn.com/problems/palindrome-linked-list/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38195449/)。
        实现方式是通过快慢指针找中点，后半段反转再比较。  
        时间复杂度 `O(n)` ；空间复杂度 `O(1)`
* 单链表反转
    * [LeetCode#206](https://leetcode-cn.com/problems/reverse-linked-list/)
        * [Java提交1](https://leetcode-cn.com/submissions/detail/38210096/)
        递归实现。空间复杂度 `O(n)`；时间复杂度 `O(n)`
        * [Java提交2](https://leetcode-cn.com/submissions/detail/38211813/)
        迭代实现。空间复杂度 `O(1)`；时间复杂度 `O(n)`
* 单链表中的环
    * [LeetCode#141](https://leetcode-cn.com/problems/linked-list-cycle/) 判断链表中是否有环
        * [Java提交](https://leetcode-cn.com/submissions/detail/37787492/)  
        快慢指针法。空间复杂度 `O(1)`；时间复杂度 `O(n)`
    * [LeetCode#142](https://leetcode-cn.com/problems/linked-list-cycle-ii/) 判断链表中是否有环。如果有环求入口节点
        * [Java提交](https://leetcode-cn.com/submissions/detail/37797598/)
        Floyd法。先用快慢指针确定是否有环。如果有环找出相遇点。分别从相遇点和头节点出发，再次相遇时即是入口节点。  
        时间复杂度 `O(n)`；空间复杂度 `O(1)`。  
        使用哈希表的方式可能更好想一些，但空间复杂度会上升到 `O(n)`
* 合并两个有序链表
    * [LeetCode#21](https://leetcode-cn.com/problems/merge-two-sorted-lists/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38219951/)
        时间复杂度：`O(n + m)`；空间复杂度 `O(1)`
* 删除链表节点
    * [LeetCode#19]() 删除链表的倒数第 N 个节点
        * [Java提交](https://leetcode-cn.com/submissions/detail/38223330/)
        时间复杂度 `O(n)`；空间复杂度 `O(1)`
    * [LeetCode#237]() 删除链表中的节点
        * [Java提交](https://leetcode-cn.com/submissions/detail/38224526/)
        这个问题是个脑筋急转弯，实际是拷贝后继节点的值，删除后继节点。时间复杂度 `O(1)`；空间复杂度 `O(1)`;
* 链表的中间节点
    * [LeetCode#876](https://leetcode-cn.com/problems/middle-of-the-linked-list/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38225643/)
        快慢指针法。时间复杂度 `O(n)`；空间复杂度 `O(1)`
## 栈
* 实现栈结构
    * Java实现
        * [基于数组的顺序int栈](./java/src/main/java/xyz/dowenliu/study/algo/_08_stack/IntArrayStack.java)
        及其[测试](./java/src/test/java/xyz/dowenliu/study/algo/_08_stack/IntArrayStackTest.java)
        * [自动扩容数组顺序int栈](./java/src/main/java/xyz/dowenliu/study/algo/_08_stack/DynamicExpandIntArrayStack.java)
        及其[测试](./java/src/test/java/xyz/dowenliu/study/algo/_08_stack/DynamicExpandIntArrayStackTest.java)
        * [基于单链表的链式int栈](./java/src/main/java/xyz/dowenliu/study/algo/_08_stack/IntLinkedStack.java)
        及其[测试](./java/src/test/java/xyz/dowenliu/study/algo/_08_stack/IntLinkedStackTest.java)
        * [基于单链表的链式泛型栈](./java/src/main/java/xyz/dowenliu/study/algo/_08_stack/LinkedStack.java)
        及其[测试](./java/src/test/java/xyz/dowenliu/study/algo/_08_stack/LinkedStackTest.java)
* 模拟浏览器的前进后退
    * Java实现
        * [使用双栈结构模拟浏览器前进后退](./java/src/main/java/xyz/dowenliu/study/algo/_08_stack/SimpleBrowser.java)
* 有效的括号
    * [LeetCode#20](https://leetcode-cn.com/problems/valid-parentheses/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38268503/)
        使用了顺序栈。时间复杂度 `O(n)`；空间复杂度 `O(n)`
* 最小栈
    * [LeetCode#155](https://leetcode-cn.com/problems/min-stack/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38274598/)
        使用链式栈。每个节点上存储节点值和节点存入时的栈最小值。时间复杂度 `O(1)`；空间复杂度 `O(n)`

* 棒球比赛
    * [LeetCode#682](https://leetcode-cn.com/problems/baseball-game/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38305791/)
        使用顺序栈解决。时间复杂度 `O(n)`；空间复杂度 `O(n)`
* 比较含退格的字符串
    * [LeetCode#844](https://leetcode-cn.com/problems/backspace-string-compare/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38306828/)
        使用顺序栈。时间复杂度 `O(n)`；空间复杂度 `O(n)`
* 删除字符串中的所有相邻重复项
    * [LeetCode#1047](https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38309066/)
        时间复杂度 `O(n)`；空间复杂度 `O(n)`
## 队列
* 实现队列结构
    * Java实现
        * [基于数组的容量固定int顺序队列](./java/src/main/java/xyz/dowenliu/study/algo/_09_queue/IntArrayQueue.java)
        及其[测试](./java/src/test/java/xyz/dowenliu/study/algo/_09_queue/IntArrayQueueTest.java)
        * [基于数组的自动扩容int顺序队列](./java/src/main/java/xyz/dowenliu/study/algo/_09_queue/DynamicIntArrayQueue.java)
        及其[测试](./java/src/test/java/xyz/dowenliu/study/algo/_09_queue/DynamicIntArrayQueueTest.java)
        * [基于数组的容量固定int循环队列](./java/src/main/java/xyz/dowenliu/study/algo/_09_queue/CircularIntArrayQueue.java)
        及其[测试](./java/src/test/java/xyz/dowenliu/study/algo/_09_queue/CircularIntArrayQueueTest.java)
        * [基于单链表的int链式队列](./java/src/main/java/xyz/dowenliu/study/algo/_09_queue/IntLinkedQueue.java)
        及其[测试](./java/src/test/java/xyz/dowenliu/study/algo/_09_queue/IntLinkedQueueTest.java)
* 最近的请求次数
    * [LeetCode#933](https://leetcode-cn.com/problems/number-of-recent-calls/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38320046/)
* 用队列实现栈
    * [LeetCode#225](https://leetcode-cn.com/problems/implement-stack-using-queues/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38323262/)  
        入栈 时间复杂度 `O(1)`；空间复杂度 `O(1)`  
        出栈、查看栈顶元素 时间复杂度 `O(n)`；空间复杂度 `O(1)`
        判断是否栈空 时间复杂度 `O(1)`；空间复杂度 `O(1)`
* 用栈实现队列
    * [LeetCode#232](https://leetcode-cn.com/problems/implement-queue-using-stacks/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38325084/)
        入队 时间复杂度 `O(1)`；空间复杂度 `O(1)`  
        出队、查看队头元素 时间复杂度 最好 `O(1)`、最差 `O(n)`、平均 `O(1)`；空间复杂度 `O(1)`  
        判断是否队空 时间复杂度 `O(1)`；空间复杂度 `O(1)`
## 排序
* 基础排序算法
    * Java实现
        * [冒泡排序](./java/src/main/java/xyz/dowenliu/study/algo/_11_sort/BobbleIntArraySort.java)
        * [优化的冒泡排序1](./java/src/main/java/xyz/dowenliu/study/algo/_11_sort/BetterBobbleIntArraySort1.java)
        通过测试可以发现性能提升极为有限
        * [优化的冒泡排序2](./java/src/main/java/xyz/dowenliu/study/algo/_11_sort/BetterBobbleIntArraySort2.java)
        通过测试可以发现性能提升极也为有限
        * [插入排序](./java/src/main/java/xyz/dowenliu/study/algo/_11_sort/InsertionIntArraySort.java)
        同样是时间复杂度 O(n) 的排序算法，插入比冒泡要快的多。
        * [选择排序](./java/src/main/java/xyz/dowenliu/study/algo/_11_sort/SelectionIntArraySort.java)
        同样是时间复杂度 O(n) 的排序算法，插入比冒泡要快的多，但比插入慢得多，而且，选择排序是不稳定排序。
        * [归并排序](./java/src/main/java/xyz/dowenliu/study/algo/_11_sort/MergeIntArraySort.java)
        * [快速排序](./java/src/main/java/xyz/dowenliu/study/algo/_11_sort/QuickIntArraySort.java)

      [测试](./java/src/test/java/xyz/dowenliu/study/algo/_11_sort/IntArraySortTest.java)
* 线性排序
    * Java实现
        * [桶排序](./java/src/main/java/xyz/dowenliu/study/algo/_13_sort/BucketSort.java)
        及其[测试](./java/src/test/java/xyz/dowenliu/study/algo/_13_sort/BucketSortTest.java)
        * [计数排序](./java/src/main/java/xyz/dowenliu/study/algo/_13_sort/IntCountingSort.java)
        及其[测试](./java/src/test/java/xyz/dowenliu/study/algo/_11_sort/IntArraySortTest.java) #testCounting()
        * [基数排序](./java/src/main/java/xyz/dowenliu/study/algo/_13_sort/IntRadixSort.java)
        及其[测试](./java/src/test/java/xyz/dowenliu/study/algo/_11_sort/IntArraySortTest.java) #testRadix()
## 二分查找
* 实现二分查找
    * Java实现
        * [简单二分查找](./java/src/main/java/xyz/dowenliu/study/algo/_15_binary_search/SimpleIntBinarySearch.java)
        及其[测试](./java/src/test/java/xyz/dowenliu/study/algo/_15_binary_search/SimpleIntBinarySearchTest.java)
        假设数组中没有重复元素
        * [查找第一个匹配的二分查找](./java/src/main/java/xyz/dowenliu/study/algo/_16_binary_search/FirstMatchingIntBinarySearch.java)
        及其[测试](./java/src/test/java/xyz/dowenliu/study/algo/_16_binary_search/FirstMatchingIntBinarySearchTest.java)
        * [查找最后匹配的二分查找](./java/src/main/java/xyz/dowenliu/study/algo/_16_binary_search/LastMatchingIntBinarySearch.java)
        及其[测试](./java/src/test/java/xyz/dowenliu/study/algo/_16_binary_search/LastMatchingIntBinarySearchTest.java)
        * [查找第一个大于等于给定值的元素的二分查找](./java/src/main/java/xyz/dowenliu/study/algo/_16_binary_search/FirstMatchingOrLargerThanIntBinarySearch.java)
        及其[测试](./java/src/test/java/xyz/dowenliu/study/algo/_16_binary_search/FirstMatchingOrLargerThanIntBinarySearchTest.java)
        * [查找最后一个小于等于给定值的元素的二分查找](./java/src/main/java/xyz/dowenliu/study/algo/_16_binary_search/LastMatchingOrLessThanIntBinarySearch.java)
        及其[测试](./java/src/test/java/xyz/dowenliu/study/algo/_16_binary_search/LastMatchingOrLessThanIntBinarySearchTest.java)
* 搜索插入位置
    * [LeetCode#35](https://leetcode-cn.com/problems/search-insert-position/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38561992/)
        二分查找的变形。查找第一个大于等于给定值的元素索引即可。如果没有找到，说明插入值比数组中所有值都大，在尾部插入，返回数组长度值。时间复杂度 `O(log(n))`；空间复杂度 `O(1)`
* 搜索旋转排序数组
    * [LeetCode#33](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38581894/)
        先查找偏移量，再带着偏移量进行二分查找。查找偏移量的过程类似二分查找，时间复杂度 `O(N)`。总体的时间复杂度 `O(n)`，空间复杂度 `O(1)`
* 在排序数组中查找元素的第一个和最后一个位置
    * [LeetCode#34](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38600438/)
        先使用二分查找找第一个位置。如果没有找到，直接返回 `[-1, -1]` 。
        如果找到第一个位置 `first`，再使用二分查找在 `first` 到 `nums.length - 1` 区间查找最后位置（这个位置一定存在，最小是`first`)。
        时间复杂度 `O(log(n))`；空间复杂度 `O(1)`
* x 的平方根
    * [LeetCode#69](https://leetcode-cn.com/problems/sqrtx/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38621334/)
        二分查找的变形。要查找的数处于一片连续整数区间`[0, x]`中，且与查找位置恰好相同，数组被省略。负数的情况特别处理，返回`-1`。
        时间复杂度 `O(log(n))`；空间复杂度 `O(1)`
* 搜索二维矩阵
    * [LeetCode#74](https://leetcode-cn.com/problems/search-a-2d-matrix/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38633925/)
        按题意，二维数组相当于平均分段的连续升序数组。使用二分查找，只需计算 线性索引对应的矩阵索引即可。
        时间复杂度 `O(log(m*n))`；空间复杂度 `O(1)`。
* 第一个错误的版本
    * [LeetCode#278](https://leetcode-cn.com/problems/first-bad-version/)
        * [Java提交](https://leetcode-cn.com/submissions/detail/38636895/)
        时间复杂度 `O(log(n))`；空间复杂度 `O(1)`
