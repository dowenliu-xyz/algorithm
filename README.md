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
* 单链表反转
    * [LeetCode#206](https://leetcode-cn.com/problems/reverse-linked-list/)
        * [提交1](https://leetcode-cn.com/submissions/detail/38210096/)
        递归实现。空间复杂度 O(n)；时间复杂度 O(n)
        * [提交2](https://leetcode-cn.com/submissions/detail/38211813/)
        迭代实现。空间复杂度 O(1)；时间复杂度 O(n)
* 单链表中的环
    * [LeetCode#141](https://leetcode-cn.com/problems/linked-list-cycle/) 判断链表中是否有环
        * [提交](https://leetcode-cn.com/submissions/detail/37787492/)  
        快慢指针法。空间复杂度 O(1)；时间复杂度 O(n)
    * [LeetCode#142](https://leetcode-cn.com/problems/linked-list-cycle-ii/) 判断链表中是否有环。如果有环求入口节点
        * [提交](https://leetcode-cn.com/submissions/detail/37797598/)
        Floyd法。先用快慢指针确定是否有环。如果有环找出相遇点。分别从相遇点和头节点出发，再次相遇时即是入口节点。  
        时间复杂度 O(n)；空间复杂度 O(1)。  
        使用哈希表的方式可能更好想一些，但空间复杂度会上升到 O(n)
* 合并两个有序链表
    * [LeetCode#21](https://leetcode-cn.com/problems/merge-two-sorted-lists/)
        * [提交](https://leetcode-cn.com/submissions/detail/38219951/)
        时间复杂度：O(n + m)；空间复杂度 O(1)
* 删除链表节点
    * [LeetCode#19]() 删除链表的倒数第 N 个节点
        * [提交](https://leetcode-cn.com/submissions/detail/38223330/)
        时间复杂度 O(n)；空间复杂度 O(1)
    * [LeetCode#237]() 删除链表中的节点
        * [提交](https://leetcode-cn.com/submissions/detail/38224526/)
        这个问题是个脑筋急转弯，实际是拷贝后继节点的值，删除后继节点。时间复杂度 O(1)；空间复杂度 O(1);
* 链表的中间节点
    * [LeetCode#876](https://leetcode-cn.com/problems/middle-of-the-linked-list/)
        * [提交](https://leetcode-cn.com/submissions/detail/38225643/)
        快慢指针法。时间复杂度 O(n)；空间复杂度 O(1)