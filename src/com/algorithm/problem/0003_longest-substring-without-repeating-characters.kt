package com.algorithm.problem

/**
 * 无重复字符的最长子串
 *      https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *      https://www.cxyxiaowu.com/6845.html
 *
 * 滑动窗口
 *      顾名思义，就像一个滑动的窗口，套在一个序列中，左右的滑动，窗口内就是一个内容集；
 *      一般用来解决查找满足一定条件的连续区间的性质（长度等）的问题。
 */
fun main() {
    lengthOfLongestSubstring("abba")
}

/**
 * 1、如果当前字符不包含在 map 中，将该字符添加到 map 中，此时没有出现重复的字符，左指针不需要变化。
 * 此时不重复子串的长度为：max(maxLen, index-left+1)
 *
 * 2、如果当前字符包含在 map 中，此时有两种情况：
 *      (1)：当前字符包含在当前有效的子段中，如：abca，当遍历到第二个 a，当前有效最长子段是 abc；
 *      紧接着又遍历到 a，那么此时更新 left 为 map[a]+1=1，当前有效子段更新为 bca；
 *      (2)：当前字符不包含在当前最长有效子段中，如：abba，先添加 a 和 b 进 map，此时 left=0，再添加 b，发现 map 中包含 b，
 *      而且b包含在最长有效子段中，就是 (1) 的情况，我们更新 left=map[b]+1=2，此时子段更新为 b，而且 map 中仍然包含 a，map[a]=0；
 *      随后再继续遍历到 a，发现 a 包含在 map 中，且 map[a]=0，如果像 (1) 一样处理，就会发现 left=map[a]+1=1，实际上 left(还是2才对) 此时应该不变，子段变成 ba；
 * 为了处理以上两种情况，每次都应该更新 left=max(left, map[value]+1)
 * 更新 left 后，不管原来的 value 是否在最长子段中，我们都要将 value 的位置更新为当前的 index，因此此时新的 value 已经进入到当前最长的子段中了。
 */
fun lengthOfLongestSubstring(string: String): Int {
    val map = mutableMapOf<Char, Int>() //key是字符，value是字符在String中的位置
    var maxLen = 0 //用于记录最大不重复子串的长度
    var left = 0 //滑动窗口左指针
    for ((index, value) in string.withIndex()) {
        println("$index-$value")
        if (map.containsKey(value)) {
            println("contain [$value], left=max($left, ${map[value]!! + 1})")
            left = left.coerceAtLeast(map[value]!! + 1)
        }
        map[value] = index //不管是否更新left，都要更新 value 的位置
        maxLen = maxLen.coerceAtLeast(index - left + 1)
        println("left=$left, maxLen=$maxLen, map=$map")
        println("------------------------------------------------")
    }
    return maxLen
}