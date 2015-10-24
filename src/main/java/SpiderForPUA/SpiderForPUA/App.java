package SpiderForPUA.SpiderForPUA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Hello world!
 *
 */
public class App 
{
	Logger logger = LoggerFactory.getLogger(App.class);
	public App(){
		logger.info("haha");
	}
    public static void main( String[] args )
    {
        System.out.println( "this is a spider" );
        App app = new App();
        
        
    }
}
