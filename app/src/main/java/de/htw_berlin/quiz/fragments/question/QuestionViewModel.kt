package de.htw_berlin.quiz.fragments.question


import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.htw_berlin.quiz.Frage
import de.htw_berlin.quiz.R
import java.util.*

class QuestionViewModel (name : String): ViewModel() {


    /**
     * all Questions
     */
    private var allQList: MutableList<Frage> = arrayListOf()


    /**
     * questions for current game
     */
    private var qList: MutableList<Frage> = arrayListOf()

    /**
     * position of question -> starst at 1. question
     */
    private var pos : Int = 1

    /**
     * selected option
     */
    var mso_pos : Int = 0


    /**
     * bool to track if user selected an answer
     */
    var answerSelected: Boolean = false


    /**Event
     *
     *LiveData is an observable data holder class that is used
     * to observe the changes of a ViewModel and update those changes.
     */

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _sname = MutableLiveData<String>()
    val sname: LiveData<String>
        get() = _sname

    private val _eventLastQ = MutableLiveData<Boolean>()
    val eventLastQ: LiveData<Boolean>
        get() = _eventLastQ

    private val _eventNextQ = MutableLiveData<Boolean>()
    val eventNextQ: LiveData<Boolean>
        get() = _eventNextQ

    private val _eventProgress = MutableLiveData<Int>()
    val progress: LiveData<Int>
        get() = _eventProgress

    private val _eventNewQ = MutableLiveData<String>()
    val newQ: LiveData<String>
        get() = _eventNewQ

    private val _eventNewA = MutableLiveData<ArrayList<String>>()
    val newA: LiveData<ArrayList<String>>
        get() = _eventNewA

    private val _eventSetDefault = MutableLiveData<Boolean>()
    val setDef: LiveData<Boolean>
        get() = _eventSetDefault

    private val _eventAnswerClicked = MutableLiveData<View>()
    val answerClicked: LiveData<View>
        get() = _eventAnswerClicked

    private val _eventChooseOption = MutableLiveData<Dictionary<Int,Int>>()
    val chooseOption: LiveData<Dictionary<Int,Int>>
        get() = _eventChooseOption

    private val _eventchangeButton = MutableLiveData<String>()
    val changeButton: LiveData<String>
        get() = _eventchangeButton

    private val _eventShowResult = MutableLiveData<Dictionary<String,Int>>()
    val showReult: LiveData<Dictionary<String,Int>>
        get() = _eventShowResult

    private val _eventEnableOptions = MutableLiveData<Boolean>()
    val eventEnableOptions: LiveData<Boolean>
        get() = _eventEnableOptions

    private val _eventDisableOptions = MutableLiveData<Boolean>()
    val eventDisbleOptions: LiveData<Boolean>
        get() = _eventDisableOptions

    private val _eventAnswerNotSelected = MutableLiveData<Boolean>()
    val eventAnswerNotSelected: LiveData<Boolean>
        get() = _eventAnswerNotSelected



    init {
        /**
         * set name from constructor
         * set default score = 0
         */
        _sname.value = name
        _score.value = 0

        /**
         * get Question for current game
         */
        getQuestion()

        /**
         * seting next question for showing in GUI
         */
        setQuestion()
    }


    fun getQuestion()
    {
        var counter: Int = 0

        /**
         * all questions
         */

        val f1 = Frage(1,
            "Gegen welchen dieser Bösewichte hat Spider-Man nie gekämpft?",
            "Green Goblin",
            "Sandman",
            "The Joker",
            "Venom",
            3)
        allQList.add(f1)

        val f2 = Frage(2,
            "Wie heißt ''He-Man'' mit richtigem Namen?",
            "Peter",
            "Adam",
            "Spencer",
            "John",
            2)
        allQList.add(f2)

        val f3 = Frage(3,
            "Wie heißt die US-Raumfähre, die am 1. Februar 2003 auf dem Weg zurück in die Erdatmosphäre explodierte?",
            "Columbia",
            "Discovery",
            "Enterprise",
            "Challenger",
            1)
        allQList.add(f3)

        val f4 = Frage(4,
            "Was wurde im Jahr 2001 als ''1000 Songs in deiner Tasche'' präsentiert?",
            "Sony MiniDisc",
            "Apples iPhone",
            "Spotify",
            "Apples iPod",
            4)
        allQList.add(f4)

        val f5 = Frage(5,
            "Wie nennt man jemanden, der von der Elfenbeinküste stammt?",
            "Elfit",
            "Beinküster",
            "Ivorer",
            "Elfenbeiner",
            3)
        allQList.add(f5)

        val f6 = Frage(6,
            "In welcher Stadt gibt es einen Hügel namens ''Kapitol''?",
            "Athen",
            "Rom",
            "Kairo",
            "Washington, D.C.",
            2)
        allQList.add(f6)

        val f7 = Frage(7,
            "Wie heißt eine berühmte Spielreihe, die in einer Zusammenarbeit zwischen Electronic Arts und BioWare entstanden ist?",
            "Dragon Hunt",
            "Dragons Flight",
            "Dragons Dogma",
            "Dragon Age",
            4)
        allQList.add(f7)

        val f8 = Frage(8,
            "Wie heißt der Erzengel Gabriel auf Arabisch?",
            "Cabriallah",
            "Jibril",
            "Djabrahal",
            "Gabbeh",
            2)
        allQList.add(f8)

        val f9 = Frage(9,
            "Wo wuchs Dagobert Duck auf?",
            "Klondike",
            "Irland",
            "Australien",
            "Schottland",
            4)
        allQList.add(f9)

        val f10 = Frage(10,
            "Bei den 15 Mitgliedsstaaten des UN-Sicherheitsrates unterscheidet man zwischen ...?",
            "formell und informell",
            "ständig und nichtständig",
            "solvent und insolvent",
            "abhängig und unabhängig",
            2)
        allQList.add(f10)

        val f11 = Frage(11,
            "Mit wem bestritt Gerhard Schröder 2002 das erste Kanzlerduell im deutschen Fernsehen?",
            "Horst Seehofer",
            "Helmut Kohl",
            "Edmund Stoiber",
            "Angela Merkel",
            3)
        allQList.add(f11)

        val f12 = Frage(12,
            "In welchem Bundesland muss man mindestens 40 Jahre alt sein, um Ministerpräsident zu werden?",
            "Bayern",
            "Niedersachsen",
            "Baden-Württemberg",
            "Nordrhein-Westfalen",
            1)
        allQList.add(f12)

        val f13 = Frage(13,
            "Was bezeichnen Juristen als „Idiotenwiese“?",
            "Zuschauerraum im Gericht",
            "Einführungskurs für Schöffen",
            "Stichwortverzeichnis",
            "Bundesjustizministerium",
            3)
        allQList.add(f13)

        val f14 = Frage(14,
            "Schlagerstar Heino heißt mit Vornamen eigentlich ...?",
            "Hein Norbert",
            "Heinrich Otto",
            "Heiko Norman",
            "Heinz Georg",
            4)
        allQList.add(f14)

        val f15 = Frage(15,
            "Elvis und Priscilla Presley ...?",
            "hatten drei Kinder",
            "lernten sich in Hessen kennen",
            "wurden im selben Jahr geboren",
            "trennten sich nach 20 Jahren",
            2)
        allQList.add(f15)

        val f16 = Frage(16,
            "Wer sagt auf keinen Fall die Wahrheit?",
            "Bücherwurm",
            "Zeitungsente",
            "Leseratte",
            "Eselsohr",
            2)
        allQList.add(f16)

        val f17 = Frage(17,
            "Was ist bei Klassikfreunden sehr bekannt?",
            "Teatro Real in Madrid",
            "Opera Arsenal in London",
            "Concerthall Ajax in Amsterdam",
            "Filarmonica AC in Milano",
            1)
        allQList.add(f17)

        val f18 = Frage(18,
            "Was wurde 2017 von der UNESCO als Kulturerbe der Menschheit anerkannt?",
            "Coq au Vin und Chanson",
            "Pizza und Orgelmusik",
            "Souflaki und Shanty",
            "Paella und Schlager",
            2)
        allQList.add(f18)

        val f19 = Frage(19,
            "Wer war Gottfried Semper, der Namensgeber der Semperoper in Dresden?",
            "Opernstar",
            "Architekt",
            "erster Intendant",
            "langjähriger Bürgermeister",
            2)
        allQList.add(f19)

        val f20 = Frage(20,
            "Legt man ein gelatinehaltiges Gummibärchen für längere Zeit in kaltes Wasser, so ...?",
            "wird es gelb",
            "schrumpft es zusammen",
            "vergrößert es sich",
            "verhärtet es sich",
            3)
        allQList.add(f20)

        val f21 = Frage(21,
            "Welcher Anlass bringt Maria und Josef laut Bibel dazu, sich auf den Weg in Richtung Betlehem zu machen?",
            "Hochzeit",
            "Volkszählung",
            "Pilgerfahrt",
            "Missernte",
            2)
        allQList.add(f21)

        val f22 = Frage(22,
            "Pankratius und Servatius sind ...?",
            "Eisheilige",
            "die ersten Jünger Jesu",
            "Nachbarstädte von Betlehem",
            "Berge des Sinai",
            1)
        allQList.add(f22)

        val f23 = Frage(23,
            "Wie werden in der römischen Mythologie die Göttinnen der Anmut genannt?",
            "drei Grazien",
            "vier Schönheiten",
            "fünf Prachtvolle",
            "sechs Wohlgestalten",
            1)
        allQList.add(f23)

        val f24 = Frage(24,
            "In seinem ersten Hörspiel, das 1977 veröffentlicht wurde, ist Benjamin Blümchen ein ...?",
            "Straßenmusikant",
            "Zoopraktikant",
            "Wetterelefant",
            "Pizzalieferant",
            3)
        allQList.add(f24)

        val f25 = Frage(25,
            "Unter welchem Slogan startete 1978 eine Kampagne für mehr Umweltbewusstsein?",
            "Weizen statt Heizen",
            "Jute statt Plastik",
            "Fahrrad statt Lenkrad",
            "Soja statt Schnitzel",
            2)
        allQList.add(f25)

        val f26 = Frage(26,
            "Was können Österreicher im Supermarkt erwerben?",
            "Zugabe",
            "Nachschlag",
            "Bonus",
            "Extrawurst",
            4)
        allQList.add(f26)

        val f27 = Frage(27,
            "Wie heißt eine Schneideart von Gemüse, bei der es in sehr feine Streifen geschnitten wird?",
            "Adrienne",
            "Lucienne",
            "Fabienne",
            "Julienne",
            4)
        allQList.add(f27)

        val f28 = Frage(28,
            "Was ist im Rheinischen unter „Flutschmoppen“ zu verstehen?",
            "Lebkuchen",
            "Gewürzgurken",
            "Wassereis",
            "Hackbällchen",
            1)
        allQList.add(f28)

        val f29 = Frage(29,
            "Welche Suppe wird typischerweise mit Drachenkopf zubereitet?",
            "Soljanka",
            "Gazpacho",
            "Bouillabaisse",
            "Miso-Suppe",
            3)
        allQList.add(f29)

        val f30 = Frage(30,
            "Aus welchem Teil des Zimtbaumes wird das handelsübliche Zimtpulver hergestellt?",
            "Rinde",
            "Blätter",
            "Wurzeln",
            "Krone",
            1)
        allQList.add(f30)

        val f31 = Frage(31,
            "Wofür ist in der deutschen Honigverordnung ein Höchstwert festgelegt?",
            "Klebintensität",
            "elektrische Leitfähigkeit",
            "Alkoholgehalt",
            "Verkaufspreis",
            2)
        allQList.add(f31)

        val f32 = Frage(32,
            "Die spanische Spezialität Sherry ist ...?",
            "verstärkter Weißwein",
            "vergorener Birnensaft",
            "gegärte Rhabarberwurzel",
            "verdünnter Portwein",
            1)
        allQList.add(f32)

        val f33 = Frage(33,
            "Scharfe Gewürze ...?",
            "beugen grauen Haaren vor",
            "machen Speisen haltbarer",
            "wirken sättigender",
            "verbessern die Kondition",
            2)
        allQList.add(f33)

        val f34 = Frage(34,
            "Welches Tier wird in der Jägersprache auch „Schmalzmann“ genannt?",
            "Hase",
            "Ente",
            "Dachs",
            "Rotfuchs",
            3)
        allQList.add(f34)

        val f35 = Frage(35,
            "Was sind Chia, Quinoa und Buchweizen?",
            "Pseudogetreide",
            "Anscheinkörner",
            "Geheimsamen",
            "Falschkräuter",
            1)
        allQList.add(f35)

        val f36 = Frage(36,
            "Was kann man sich zu Hause auf die Fensterbank stellen?",
            "Scheinrose",
            "Groschenblume",
            "Geldbaum",
            "Münzpalme",
            3)
        allQList.add(f36)

        val f37 = Frage(37,
            "Wie Forscher in Experimenten nachweisen konnten, erkennen sich Schimpansen untereinander ...?",
            "an den Ohren",
            "an den Augen",
            "an den Händen",
            "an dem Hintern",
            4)
        allQList.add(f37)

        val f38 = Frage(38,
            "Eine Jakobsmuschel hat ...?",
            "lange Haare",
            "blaue Augen",
            "schiefe Zähne",
            "rote Lippen",
            2)
        allQList.add(f38)

        val f39 = Frage(39,
            "Welches Tier gibt es wirklich?",
            "Naschbär",
            "Naschkatze",
            "Naschvogel",
            "Naschsau",
            3)
        allQList.add(f39)

        val f40 = Frage(40,
            "Hühner nicken beim Laufen mit dem Kopf, um ...?",
            "Fressfeinde abzuschrecken",
            "Luft zu bekommen",
            "besser zu sehen",
            "Gerüche aufzunehmen",
            3)
        allQList.add(f40)

        val f41 = Frage(41,
            "Hatha und Kundalini sind auch hierzulande beliebte ...?",
            "Gruppentänze",
            "Kampfkünste",
            "Ballspiele",
            "Yogastile",
            4)
        allQList.add(f41)

        val f42 = Frage(42,
            "In welchem Teil des Ohrs befindet sich die Cochlea, das eigentliche Hörorgan des Menschen?",
            "Ohrmuschel",
            "äußeres Ohr",
            "Mittelohr",
            "Innenohr",
            4)
        allQList.add(f42)

        val f43 = Frage(43,
            "Welcher nervöse Zustand kann Profis beim Ausüben ihrer Sportart behindern?",
            "Kegelitis",
            "Golfitis",
            "Dartitis",
            "Pokeritis",
            3)
        allQList.add(f43)

        val f44 = Frage(44,
            "Mit welcher Idee sorgte die damalige CSU-Rebellin Gabriele Pauli im Jahr 2007 für Schlagzeilen?",
            "Haustierpflicht für Familien",
            "Ehe auf Zeit",
            "Abschaffung aller Feiertage",
            "Unabhängigkeit Bayerns",
            2)
        allQList.add(f44)


        val f45 = Frage(45,
            "Womit beschäftigt man sich, wenn man die Computer-Programme Ableton Live, Nuendo oder Pro Tools verwendet?",
            "Videoproduktion",
            "Bildbearbeitung",
            "3D-Animation",
            "Musikproduktion",
            4)
        allQList.add(f45)


        val f46 = Frage(46,
            "Wofür steht die Abkürzung LAN in der Computer-Sprache?",
            "Loop Algrrithm Node",
            "Local Area Network",
            "Logic Area Node",
            "Logic Area Network",
            2)
        allQList.add(f46)

        val f47 = Frage(47,
            "Wo hat die deutsche Telekom ihren Hauptsitz?",
            "Berlin",
            "Frankfurt am Main",
            "Hamburg",
            "Bonn",
            4)
        allQList.add(f47)

        val f48 = Frage(48,
            "Wo genau am Zylinder ist die Zündkerze bei einem Ottomotor angebracht?",
            "Am Zylinderkopf",
            "An der Einspritzpumpe",
            "Am Auspuff",
            "Am Vergaser",
            1)
        allQList.add(f48)

        val f49 = Frage(49,
            "Wo findet man einen „Crossfaden“ ?",
            "DJ-Mischpult",
            "Online Banking",
            "Kfz-Hydraulik",
            "Smartphone Display",
            1)
        allQList.add(f49)

        val f50 = Frage(50,
            "Wie heißt der 184 m hohe Aussichtsturm in Seattle, der 1962 errichtet wurde?",
            "Sky Point",
            "Space Needle",
            "Air View",
            "Cloud Scratcher",
            2)
        allQList.add(f50)


        /**
         * choose 5 random Question
         */
        while( counter<5)
        {
            val randomElement = allQList.random()

            if(!qList.contains(randomElement))
            {
                qList.add(randomElement)
                counter++
            }
        }

        counter  = 0

    }

    private fun setQuestion()
    {

        /**
         * enable that answer options can be clicked
         */
        _eventEnableOptions.value = true

        /**
         * next question from list
         **/

        val questionQ = qList!![pos -1]

        /**
         * trigger event for setteing  default color for the answer options
         */

        _eventSetDefault.value = true


        /**
         * trigger event for setting the text of the answerButton
         */
        _eventNextQ.value = true

        /**
         *trigger event for updating progressbar
         */
        _eventProgress.value = pos

        /**
         * trigger event for setting next question in GUI
         */

        _eventNewQ.value = questionQ!!.frage


        /**
         * answer list with answer options for the current question
         * */

        var answer_list = ArrayList<String>()

        answer_list.add(questionQ!!.option1)
        answer_list.add(questionQ!!.option2)
        answer_list.add(questionQ!!.option3)
        answer_list.add(questionQ!!.option4)


        /**
         * trigger event for setting the answer options for the current question
         */
        _eventNewA.value = answer_list
    }

    /**
     * track which answer was selected by the user
     */
    fun onAnswer(v: View)
    {

        /**
         * user selected an answer
         */
        answerSelected = true

        /**
         * mso_pos = selected option
         * save by tag which option was selected
         */
        mso_pos = v.tag.toString().toInt()


        /**
         * trigger event for setting color of selected option
         */
        _eventAnswerClicked.value = v
    }


    /**
     * onAnswerButton starts when user clicks the answer button
     */
    fun onAnswerButton()
    {
        /**
         * answer must be selected  to go further
         */
        if(answerSelected == true)
        {
            /**
             * when the user selects the option and presses the answer botton mso_pos tracks the option the user has selected  (mso_pos != 0)
             * after the user presses the answer button again to go to next question mso_pos = 0
             */
            if(mso_pos == 0)
            {
                /**
                 * pos  tracks the questions
                 */
                pos++

                /**
                 * if not the last question
                 */
                when{
                    pos <= qList!!.size ->
                    {
                        setQuestion()

                        answerSelected = false


                        /**
                         * if current questions IS the last question, save the name of the user and the score and
                         * navigate to result fragment
                         */
                    }else
                    -> {

                    var resDict: Dictionary<String, Int> = Hashtable()
                    resDict.put(sname.getValue().toString(),score.getValue())
                    _eventShowResult.value = resDict
                    }
                }
            }
            /**
             * this else block will be executed whe mso_pos !=0 what means that the user has selected an answer
             * and logged it as the final answer that cant be changed anymore --> mso_pos = the selected option
             * after this else Block the user has to cklick the answer button again to go to next question --> mso_pos will be 0
             */
            else
            {
                var dict: Dictionary<Int, Int> = Hashtable()

                /**
                 * current question
                 */
                val q = qList?.get(pos - 1)

                /**
                 * if selected option is wrong
                 */

                if(q!!.antwort != mso_pos)
                {

                    dict.put(mso_pos, R.drawable.wrong_option)

                    /**
                     * trigger event for setting "wrong" - color to the selected option inGUI
                     */
                    _eventChooseOption.value = dict

                    dict.remove(mso_pos)

                }
                /**
                 * if selected option is correct increase score
                 */
                else{
                    _score.value = (_score.value)?.plus(1)
                }

                /**
                 * trigger event to set "correct" - color to the correct answer option in GUI
                 */

                dict.put(q.antwort, R.drawable.correct_option)
                _eventChooseOption.value = dict
                dict.remove(q.antwort)


                /**
                 * trigger event to disable that answer options can be clicked
                 */
                _eventDisableOptions.value = true


                /**
                 * trigger event to change text of answer button
                 */
                if(pos == qList!!.size && _eventDisableOptions.value == true)
                {
                    _eventchangeButton.value = "ZUM ERGEBNIS"

                }
                else if( _eventDisableOptions.value == true)
                {
                    _eventchangeButton.value = "NÄCHSTE FRAGE"
                }
                else
                {
                    _eventchangeButton.value = "ANTWORTEN"
                }
                /**
                 * set mso_pos back to 0 for setting next question
                 */
                mso_pos = 0
            }
        }
        else
        {
            /**
             * if answer not selected before clicking answer button
             * trigger the event to show Toast to the user
             * answer option must be selected before clicking answer button
             */

            _eventAnswerNotSelected.value = true
        }
    }

}
