fun main() {
    var player = 'O'
    var rc: Array<Int>
    var boardXO = createBoard()
    var turn = 0

    while (turn < 9) {
        player = switchTurn(player)
        rc = inputRC(boardXO)
        updateTable(rc, player, boardXO)
        if (checkWin(boardXO, player)) {
            println("GAME OVER! Player ${player} WIN")
            break
        }
        turn++
    }
    if (turn == 9) print("GAME OVER! DRAW")

}

fun inputRC(boardXO: Array<Array<Char>>): Array<Int> {
    while (true) {
        try {
            print("Please input r c : ")
            val input = readLine()
            val inputarr = input?.split(' ')

            if (inputarr?.size != 2) {
                println("Error...size")
                continue
            }
            val row = inputarr.get(0).toInt()
            val col = inputarr.get(1).toInt()
            if (row > 3 || row < 1 || col > 3 || col < 1) {
                println("Error...number")
                continue
            }
            if (boardXO[row][col] != '-') {
                println("Repeat position!! Please enter a new.")
                continue
            }
            val arrInput = arrayOf(row, col)
            return arrInput
            break

        } catch (t: Throwable) {
            println("Error...type")
        }
    }
}

fun createBoard(): Array<Array<Char>> {
    val boardXO: Array<Array<Char>> = arrayOf(
        arrayOf(' ', '1', '2', '3'),
        arrayOf('1', '-', '-', '-'),
        arrayOf('2', '-', '-', '-'),
        arrayOf('3', '-', '-', '-')
    )
    for (row in boardXO) {
        for (col in row) {
            print("$col ")
        }
        println()
    }
    return boardXO
}

fun updateTable(rc: Array<Int>, player: Char, boardXO: Array<Array<Char>>) {
    boardXO[rc.get(0)][rc.get(1)] = player;
    for (row in boardXO) {
        for (col in row) {
            print("$col ")
        }
        println()
    }
}

fun switchTurn(player: Char): Char {
    if (player == 'O') {
        println("Turn Player : X")
        return 'X'
    } else {
        println("Turn Player : O")
        return 'O'
    }
}

fun checkWin(boardXO: Array<Array<Char>>, player: Char): Boolean {
    var sum = 0
    var win = false
//    - row win
    for (row in 0 until 4) {
        for (col in 0 until 4) {
            if (boardXO[row][col] == player) sum++
            if (sum == 3) win = true
        }
        sum = 0
    }
//   | col win
    for (row in 0 until 4) {
        for (col in 0 until 4) {
            if (boardXO[col][row] == player) sum++
            if (sum == 3) win = true
        }
        sum = 0
    }
//    \ win
    if (boardXO[1][1] == player && boardXO[2][2] == player && boardXO[3][3] == player) win = true
//    / win
    if (boardXO[3][1] == player && boardXO[2][2] == player && boardXO[1][3] == player) win = true

    return win
}
