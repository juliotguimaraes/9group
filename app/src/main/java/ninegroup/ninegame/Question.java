package ninegroup.ninegame;

/**
 * Created by guilherme on 20/11/15.
 */
public class Question {
    private String question; // enunciado da questao
    private String alternativeA,alternativeB,alternativeC,alternativeD,alternativeE; // enunciado de cada alternativa
    private String correctAlternative; // este campo pode ter os valores A,B,C,D ou E indicando qual das alternativas eh a correta
    public Question(String question, String alternativeA, String alternativeB, String alternativeC, String alternativeD, String alternativeE, String correctAlternative) {
        this.question = question;
        this.alternativeA = alternativeA;
        this.alternativeB = alternativeB;
        this.alternativeC = alternativeC;
        this.alternativeD = alternativeD;
        this.alternativeE = alternativeE;
        this.correctAlternative = correctAlternative;
    }

    public String getQuestion() {
        return question;
    }
    public String getAlternativeA() {
        return alternativeA;
    }
    public String getAlternativeB() {
        return alternativeB;
    }
    public String getAlternativeC() {
        return alternativeC;
    }
    public String getAlternativeD() {
        return alternativeD;
    }
    public String getAlternativeE() {
        return alternativeE;
    }
    public String getCorrectAlternative() {
        return correctAlternative;
    }
    public boolean isTheCorrectAnswer(String alternative){
        return correctAlternative.equals(alternative);
    }
}
