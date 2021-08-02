package com.algorithm.problem

import com.algorithm.model.LinkedNode

/**
 * 两数相加
 *      https://leetcode-cn.com/problems/add-two-numbers/
 *      https://www.cxyxiaowu.com/6843.html
 */

fun main() {

    val l1 = LinkedNode(2, LinkedNode(4, LinkedNode(3, null)))
    val l2 = LinkedNode(5, LinkedNode(6, LinkedNode(4, null)))

    printLinkedList(l1)
    printLinkedList(l2)

    val head = addTwoNumbers(l1, l2)
    printLinkedList(head)

}

/**
 * 注意：俩链表长度不一致时，往短链表后补0，此处不考虑
 */
fun addTwoNumbers(l1: LinkedNode?, l2: LinkedNode?): LinkedNode? {
    if (l1 == null) {
        return l2
    }
    if (l2 == null) {
        return l1
    }
    val dummyHead = LinkedNode(-1, null)
    //表示进位的变量carry
    var carry = 0
    var dummyL1 = l1
    var dummyL2 = l2
    var curHead = dummyHead
    while (dummyL1 != null && dummyL2 != null) {
        val value1 = dummyL1.value
        val value2 = dummyL2.value
        //【俩链表的value和carry相加后取余】，得到的值作为一个新节点存到新链表后面
        val dummyValue = (value1 + value2 + carry) % 10
        println("$value1 + $value2 = ${value1 + value2}, dummyValue = $dummyValue")
        curHead.next = LinkedNode(dummyValue, null)
        curHead = curHead.next!!
        //【俩链表的value和carry相加后取整】，得到的就是进位的值
        carry = (value1 + value2 + carry) / 10
        //移动链表
        dummyL1 = dummyL1.next
        dummyL2 = dummyL2.next
    }
    return dummyHead.next
}

/**
 * 链表遍历
 */
fun printLinkedList(head: LinkedNode?) {
    var current = head
    while (current != null) {
        print("${current.value} -> ")
        current = current.next
    }
    println()
}