package de.woock.ddd.stattauto.fuhrpark;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractTest {

	protected Logger log = Logger.getLogger(getClass());
}
