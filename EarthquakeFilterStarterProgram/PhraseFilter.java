
/**
 * 在这里给出对类 PhraseFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class PhraseFilter implements Filter {
    private String where;
    private String phrase;
    private String name;
    public PhraseFilter(String where, String phrase) {
        this.where = where;
        this.phrase = phrase;
        name = "";
    }
    public PhraseFilter(String where, String phrase,String name) {
        this.where = where;
        this.phrase = phrase;
        this.name = name;        
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        if (where.equals("start")) {
            return title.startsWith(phrase);
        } else if (where.equals("end")) {
            return title.endsWith(phrase);
        } else if (where.equals("any")) {
            return title.contains(phrase);
        }
        return false;
    }
    @Override
    public String getName() {
        return name;
    }
}

