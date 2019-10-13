package ru.movchinnikov.patterns

import ru.movchinnikov.patterns.behavioral.observer.Editor
import ru.movchinnikov.patterns.structural.bridge.*
import ru.movchinnikov.patterns.creational.singleton.Singleton
import ru.movchinnikov.patterns.behavioral.template_method.Twitter
import ru.movchinnikov.patterns.behavioral.template_method.Facebook
import ru.movchinnikov.patterns.behavioral.template_method.Network
import ru.movchinnikov.patterns.creational.builder.CarManualBuilder
import ru.movchinnikov.patterns.creational.builder.CarBuilder
import ru.movchinnikov.patterns.creational.builder.Director
import ru.movchinnikov.patterns.creational.prototype.Shape
import ru.movchinnikov.patterns.creational.prototype.Circle
import ru.movchinnikov.patterns.creational.prototype.Rectangle
import ru.movchinnikov.patterns.structural.facade.VideoConversionFacade
import ru.movchinnikov.patterns.structural.adapter.SquarePegAdapter
import ru.movchinnikov.patterns.structural.adapter.SquarePeg
import ru.movchinnikov.patterns.structural.adapter.RoundPeg
import ru.movchinnikov.patterns.structural.adapter.RoundHole
import ru.movchinnikov.patterns.behavioral.observer.EmailNotificationListener
import ru.movchinnikov.patterns.behavioral.observer.LogOpenListener
import ru.movchinnikov.patterns.behavioral.chain.*
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {

    singleton()

    //builder()

    //prototype()



    //bridge()

    //facade()

    //adapter()


    //templateMethod()

    //observer()

    //chain()

}

fun singleton() {
    println(
        """If you see the same value, then singleton was reused
If you see different values, then 2 singletons were created
RESULT:"""
    )
    val singleton = Singleton.getInstance("FOO")
    val anotherSingleton = Singleton.getInstance("BAR")
    println(singleton.value)
    println(anotherSingleton.value)
}

fun builder() {
    val director = Director()

    // Директор получает объект конкретного строителя от клиента
    // (приложения). Приложение само знает какой строитель использовать,
    // чтобы получить нужный продукт.
    val builder = CarBuilder()
    director.constructSportsCar(builder)

    // Готовый продукт возвращает строитель, так как Директор чаще всего не
    // знает и не зависит от конкретных классов строителей и продуктов.
    val car = builder.getResult()
    println("Car built: ${car.type}")

    // Директор может знать больше одного рецепта строительства.

    val sportCarManualBuilder = CarManualBuilder()
    director.constructSportsCar(sportCarManualBuilder)
    val sportCarManual = sportCarManualBuilder.getResult()
    println("\nCar manual built: ${sportCarManual.print()}")

    val cityCarManualBuilder = CarManualBuilder()
    director.constructCityCar(cityCarManualBuilder)
    val cityCarManual = cityCarManualBuilder.getResult()
    println("\nCar manual built: ${cityCarManual.print()}")

    val suvManualBuilder = CarManualBuilder()
    director.constructSUV(suvManualBuilder)
    val suvCarManual = suvManualBuilder.getResult()
    println("\nCar manual built: ${suvCarManual.print()}")
}

fun prototype() {
    val shapes = ArrayList<Shape>()
    val shapesCopy = ArrayList<Shape>()

    val circle = Circle()
    circle.x = 10
    circle.y = 20
    circle.radius = 15
    circle.color = "red"
    shapes.add(circle)

    val anotherCircle = circle.clone() as Circle
    shapes.add(anotherCircle)

    val rectangle = Rectangle()
    rectangle.width = 10
    rectangle.height = 20
    circle.color = "blue"
    shapes.add(rectangle)

    cloneAndCompare(shapes, shapesCopy)
}

private fun cloneAndCompare(shapes: List<Shape>, shapesCopy: MutableList<Shape>) {
    for (shape in shapes) {
        shapesCopy.add(shape.clone())
    }

    for (i in shapes.indices) {
        if (shapes[i] !== shapesCopy[i]) {
            println("$i: Shapes are different objects (yay!)")
            if (shapes[i] == shapesCopy[i]) {
                println("$i: And they are identical (yay!)")
            } else {
                println("$i: But they are not identical (booo!)")
            }
        } else {
            println("$i: Shape objects are the same (booo!)")
        }
    }
}

fun adapter() {
    // Круглое к круглому — всё работает.
    val hole = RoundHole(5.0)
    val rpeg = RoundPeg(5.0)
    if (hole.fits(rpeg)) {
        println("Round peg r5 fits round hole r5.")
    }

    val smallSqPeg = SquarePeg(2.0)
    val largeSqPeg = SquarePeg(20.0)
    // hole.fits(smallSqPeg); // Не скомпилируется.

    // Адаптер решит проблему.
    val smallSqPegAdapter = SquarePegAdapter(smallSqPeg)
    val largeSqPegAdapter = SquarePegAdapter(largeSqPeg)
    if (hole.fits(smallSqPegAdapter)) {
        println("Square peg w2 fits round hole r5.")
    }
    if (!hole.fits(largeSqPegAdapter)) {
        println("Square peg w20 does not fit into round hole r5.")
    }
}




fun bridge() {
    testDevice(TV())
    testDevice(Radio())
}

private fun testDevice(device: Device) {
    println("Tests with basic remote.")
    val basicRemote = BasicRemote(device)
    basicRemote.power()
    device.printStatus()

    println("Tests with advanced remote.")
    val advancedRemote = AdvancedRemote(device)
    advancedRemote.power()
    advancedRemote.mute()
    device.printStatus()
}

fun facade() {
    val converter = VideoConversionFacade()
    converter.convertVideo("youtubevideo.ogg", "mp4")
}



fun templateMethod() {

    var network : Network? = null
    print("Input user name: ")
    val userName = readLine()!!
    print("Input password: ")
    val password = readLine()!!

    // Вводим сообщение.
    print("Input message: ")
    val message = readLine()!!
    println("""
Choose social network for posting message.
1 - Facebook
2 - Twitter
""")
    val choice = readLine()?.toInt()

    // Создаем сетевые объекты и публикуем пост.
    if (choice == 1) {
        network = Facebook(userName, password)
    } else if (choice == 2) {
        network = Twitter(userName, password)
    }
    network!!.post(message)
}

fun observer() {
    val editor = Editor()
    editor.events.subscribe("open", LogOpenListener("/path/to/log/file.txt"))
    editor.events.subscribe("save", EmailNotificationListener("admin@example.com"))

    try {
        editor.openFile("test.txt")
        editor.saveFile()
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun chain() {

    val reader = BufferedReader(InputStreamReader(System.`in`))

    val server = Server()
    server.register("admin@example.com", "admin_pass")
    server.register("user@example.com", "user_pass")

    // Проверки связаны в одну цепь. Клиент может строить различные цепи,
    // используя одни и те же компоненты.
    val middleware = ThrottlingMiddleware(2)
        .linkWith(UserExistsMiddleware(server))
        .linkWith(RoleCheckMiddleware())


    // Сервер получает цепочку от клиентского кода.
    server.middleware = middleware

    var success: Boolean
    do {
        print("Enter email: ")
        val email = reader.readLine()
        print("Input password: ")
        val password = reader.readLine()
        success = server.logIn(email, password)
    } while (!success)
}
