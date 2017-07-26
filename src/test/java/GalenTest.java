import com.galenframework.junit.GalenSpecRunner;
import com.galenframework.junit.Size;
import com.galenframework.junit.Spec;
import com.galenframework.junit.Url;
import org.junit.runner.RunWith;

/**
 * Created by Yuvaraj on 23/07/2017.
 */
@RunWith(GalenSpecRunner.class)
@Size(width = 640, height = 480)
@Spec("src/test/resources/specs/homepage_small.gspec")
@Url("http://google.com")
public class GalenTest {
}