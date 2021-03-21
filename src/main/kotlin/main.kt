import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

fun getRandomWord(filename: String) : String{
    val possibleWords : ArrayList<String> = arrayListOf()
    for (line in File(filename).readLines()) {
        if(line.contains('-') || line.length < 6)
            continue
        possibleWords.add(line)
    }
    if(possibleWords.isEmpty())
        throw Exception("Empty file")
    return possibleWords.shuffled()[0]
}

fun writeToFile(filename: String, words: ArrayList<String>) {
    val writer = File(filename).writer()
    words.forEach{
        writer.write(it)
        writer.appendLine()
    }
    writer.close()
}

fun isInclude(first: String, second: String) : Boolean{
    val letters : ArrayList<Char> = first.toCharArray().toList() as ArrayList<Char>
    second.forEach {
        if (letters.contains(it)){
            letters.remove(it)
        }
        else{
            return false
        }
    }
    return true

}

suspend fun scoringPoints(filename: String, words: ArrayList<String>) : Int{
    var scope = 0
    for (line in File(filename).readLines()) {
        if(words.contains(line))
            scope += line.length
    }
    return scope
}

fun main(args: Array<String>) {
    val startWord = getRandomWord("words.txt")

    println("Введите слова которые состоят из букв слова $startWord")
    println("В конце введите '-' для выхода")
    val inputWords : ArrayList<String> = arrayListOf()
    while(true)
    {
        val inputWord = readLine() ?: break
        if(inputWord == "-")
            break
        if(isInclude(startWord, inputWord)) {
            if (inputWords.contains(inputWord)){
                println("Это слово уже введено")
            }
            else{
                inputWords.add(inputWord)
            }
        }
        else{
            println("Это неподходящее слово")
        }
    }

    writeToFile("newWords.txt", inputWords)
    var score = 0
    GlobalScope.launch {
        score = withContext(Dispatchers.Default) {
            scoringPoints(
                "words.txt",
                inputWords
            )
        }
    }

    println("Ваш счёт: $score")
}
