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
        * [提交1](https://leetcode-cn.com/submissions/detail/38200091/)  
        空间复杂度 O(1)；时间复杂度 最好 O(m + n) 最坏 O(mn)
        * [提交2](https://leetcode-cn.com/submissions/detail/38201053/)  
        空间复杂度 O(m)；时间复杂度 O(m + n)
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
        * [提交](https://leetcode-cn.com/submissions/detail/38195449/)。  
        实现方式是通过快慢指针找中点，后半段反转再比较。  
        时间复杂度 O(n) ；空间复杂度 O(1)