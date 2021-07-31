/**
 * 两数之和：
 *      https://leetcode-cn.com/problems/two-sum/
 *      https://www.cxyxiaowu.com/6840.html
 */

fun main() {
    val arr = intArrayOf(2, 11, 7, 15)
    val target = 9

    println(twoSum(arr, target).contentToString())
}

/**
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
fun twoSum(intArray: IntArray, target: Int): Array<Int> {
    //定义map：key是数组中的value，value是数组中的index
    val map = mutableMapOf<Int, Int>()
    //循环遍历数组
    for ((index, value) in intArray.withIndex()) {
        //获取【目标值】与【当前值】的【差值】
        val difference = target - value
        //在map中查找是否有与【差值】一致的值
        val differenceInMap = map.getOrDefault(difference, -1)
        if (differenceInMap == -1) {
            //未找到，则保存到map中
            map[value] = index
        } else {
            //找到则返回两个数在数组中的索引
            return arrayOf(differenceInMap, index)
        }
    }
    return emptyArray()
}