
/**
 * 在这里给出对接口 DirectorsFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class DirectorsFilter implements Filter {
    private String[] myDirectors;

    public DirectorsFilter(String directors) {
        myDirectors = directors.split(",");
    }

    @Override
    public boolean satisfies(String id) {
        for (String director : myDirectors) {
            if (MovieDatabase.getDirector(id).contains(director.trim())) {
                return true;
            }
        }
        return false;
    }
}

