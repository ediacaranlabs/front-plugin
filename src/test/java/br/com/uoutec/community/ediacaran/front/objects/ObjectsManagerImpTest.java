package br.com.uoutec.community.ediacaran.front.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.uoutec.community.ediacaran.front.objects.ObjectsManager.ObjectEntry;

public class ObjectsManagerImpTest {

	private static final File BASE = new File(System.getProperty("user.dir"));
	
	private static final File BASE_BJECTS = new File(BASE, ObjectsManagerImp.OBJECTS_REPOSITORY);
	
	private ObjectsManagerImp objectsManager;
	
	private ObjectsManagerDriver driver;
	
	private FileManager fileManager;
	
	public boolean clearRepository(File directoryToBeDeleted) {
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	        	clearRepository(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}
	
	@BeforeEach
	public void before() throws ObjectsManagerDriverException {
		
		System.setProperty("app.base", BASE.getAbsolutePath());
		
		BASE_BJECTS.mkdir();
		clearRepository(BASE_BJECTS);
		
		fileManager = new FileManager(BASE_BJECTS, new ObjectsFileManagerHandler());
		
		driver = new FileObjectsManagerDriver(fileManager, "global");
		driver.setDefaultObjectHandler(new ObjectHandlerImp());

		objectsManager = new ObjectsManagerImp();
		objectsManager.registerDriver(driver);
	}

	@AfterEach
	public void after() {
		objectsManager = null;
		driver = null;
		fileManager = null;
		clearRepository(BASE_BJECTS);
	}
	
	@Test
	public void testRegisterDefault() {
		objectsManager.registerObject("global/path/type/item1", null, "TESTE");
		assertTrue(new File(BASE_BJECTS, "/path/type/item1_json_default.obj").exists());
	}

	@Test
	public void testRegisterDefaultAndpt_BR() {
		objectsManager.registerObject("global/path/type/item1", null, "TESTE");
		objectsManager.registerObject("global/path/type/item1", new Locale("pt", "BR"), "TESTE");
		assertTrue(new File(BASE_BJECTS, "/path/type/item1_json_default.obj").exists());
		assertTrue(new File(BASE_BJECTS, "/path/type/item1_json_pt_BR.obj").exists());
	}

	@Test
	public void testRegisterSpaceDefaultAndpt_BR() {
		objectsManager.registerObject("global/path/type/item1_val", null, "TESTE");
		objectsManager.registerObject("global/path/type/item1_val", new Locale("pt", "BR"), "TESTE");
		assertTrue(new File(BASE_BJECTS, "/path/type/item1_val_json_default.obj").exists());
		assertTrue(new File(BASE_BJECTS, "/path/type/item1_val_json_pt_BR.obj").exists());
	}

	@Test
	public void testGet() {
		objectsManager.registerObject("global/path/type/item1", null, "TESTE");
		objectsManager.registerObject("global/path/type/item1", new Locale("pt", "BR"), "PT_BR");
		objectsManager.registerObject("global/path/type/item1_val", null, "VAL1_DEFAULT");
		objectsManager.registerObject("global/path/type/item1_val", new Locale("pt", "BR"), "VAL1_PT_BR");
		
		assertTrue(new File(BASE_BJECTS, "/path/type/item1_json_default.obj").exists());
		assertTrue(new File(BASE_BJECTS, "/path/type/item1_json_pt_BR.obj").exists());
		assertTrue(new File(BASE_BJECTS, "/path/type/item1_val_json_default.obj").exists());
		assertTrue(new File(BASE_BJECTS, "/path/type/item1_val_json_pt_BR.obj").exists());
		
		objectsManager.flush();
		
		assertEquals("TESTE",objectsManager.getObject("global/path/type/item1"));
		assertEquals("PT_BR",objectsManager.getObject("global/path/type/item1", new Locale("pt", "BR")));
		assertEquals("VAL1_DEFAULT",objectsManager.getObject("global/path/type/item1_val"));
		assertEquals("VAL1_PT_BR",objectsManager.getObject("global/path/type/item1_val", new Locale("pt", "BR")));
		
		assertEquals("TESTE",objectsManager.getObject("global/path/type/item1"));
		assertEquals("PT_BR",objectsManager.getObject("global/path/type/item1", new Locale("pt", "BR")));
		assertEquals("VAL1_DEFAULT",objectsManager.getObject("global/path/type/item1_val"));
		assertEquals("VAL1_PT_BR",objectsManager.getObject("global/path/type/item1_val", new Locale("pt", "BR")));
		
	}
	
	@Test
	public void testGetReload() throws ObjectsManagerDriverException {
		
		objectsManager.registerObject("global/path/type/item1", null, "TESTE");
		
		objectsManager.flush();
		
		assertEquals("TESTE",objectsManager.getObject("global/path/type/item1"));
		assertEquals("TESTE",objectsManager.getObject("global/path/type/item1"));
		
		driver.persist("/path/type", "item1", null, "NEW VALUE");
		
		assertEquals("NEW VALUE",objectsManager.getObject("global/path/type/item1"));
		
	}

	@Test
	public void testUnregister() throws IOException {
		objectsManager.registerObject("global/path/type/item1", null, "TESTE");
		objectsManager.registerObject("global/path/type/item1", new Locale("pt", "BR"), "PT_BR");
		
		assertTrue(new File(BASE_BJECTS, "/path/type/item1_json_default.obj").exists());
		assertTrue(new File(BASE_BJECTS, "/path/type/item1_json_pt_BR.obj").exists());
		
		objectsManager.flush();
		
		assertEquals("TESTE",objectsManager.getObject("global/path/type/item1"));
		assertEquals("PT_BR",objectsManager.getObject("global/path/type/item1", new Locale("pt", "BR")));
		assertEquals("TESTE",objectsManager.getObject("global/path/type/item1"));
		assertEquals("PT_BR",objectsManager.getObject("global/path/type/item1", new Locale("pt", "BR")));
		
		objectsManager.unregisterObject("global/path/type/item1", new Locale("pt", "BR"));

		assertTrue(new File(BASE_BJECTS, "/path/type/item1_json_default.obj").exists());
		assertFalse(new File(BASE_BJECTS, "/path/type/item1_json_pt_BR.obj").exists());
		
		
	}

	@Test
	public void testUnregisterOne() throws IOException {
		objectsManager.registerObject("global/path/type/item1", null, "TESTE");
		
		assertTrue(new File(BASE_BJECTS, "/path/type/item1_json_default.obj").exists());
		
		objectsManager.flush();
		
		assertEquals("TESTE",objectsManager.getObject("global/path/type/item1"));
		assertEquals("TESTE",objectsManager.getObject("global/path/type/item1"));
		
		objectsManager.unregisterObject("global/path/type/item1", null);

		assertFalse(new File(BASE_BJECTS, "/path/type/item1_json_default.obj").exists());
		
	}
	
	@Test
	public void testGetObjects() {
		objectsManager.registerObject("global/path/type/item1", null, "TESTE");
		objectsManager.registerObject("global/path/type/item1", new Locale("pt", "BR"), "PT_BR");
		objectsManager.registerObject("global/path/type/item1_val", null, "VAL1_DEFAULT");
		objectsManager.registerObject("global/path/type/item1_val", new Locale("pt", "BR"), "VAL1_PT_BR");
		objectsManager.registerObject("global/path/type/item1_val", new Locale("en", "US"), "VAL1_EN_US");
		
		ObjectEntry e = objectsManager.getObjects("global/path/type/item1_val");
		
		assertEquals("/path/type/item1_val",e.getFullId());
		assertEquals("item1_val",e.getId());
		assertEquals("/path/type",e.getPath());
		assertEquals("VAL1_DEFAULT",e.getObject());
		assertEquals("VAL1_PT_BR",e.getObject(new Locale("pt", "BR")));
		assertEquals("VAL1_EN_US",e.getObject(new Locale("en", "US")));
	}

	@Test
	public void testList() {
		objectsManager.registerObject("global/path/item1", null, "VALOR 1");
		objectsManager.registerObject("global/path/type/item1", null, "VALOR 2");
		objectsManager.registerObject("global/path/item2", null, "VALOR 3");
		objectsManager.registerObject("global/path/type/item2_val", null, "VALOR 4");
		
		List<Object> list = objectsManager.list("global", null, null, true);
		
		assertTrue(list.size() == 4);
		assertTrue(list.indexOf("VALOR 1") != -1);
		assertTrue(list.indexOf("VALOR 2") != -1);
		assertTrue(list.indexOf("VALOR 3") != -1);
		assertTrue(list.indexOf("VALOR 4") != -1);
	}

	@Test
	public void testListPath() {
		objectsManager.registerObject("global/path/item1", null, "VALOR 1");
		objectsManager.registerObject("global/path/type/item1", null, "VALOR 2");
		objectsManager.registerObject("global/path/item2", null, "VALOR 3");
		objectsManager.registerObject("global/path/type/item2_val", null, "VALOR 4");
		
		List<Object> list = objectsManager.list("global/path", "item1", null, false);
		
		assertTrue(list.size() == 1);
		assertTrue(list.indexOf("VALOR 1") != -1);
	}
	
	@Test
	public void testListRecursive() {
		objectsManager.registerObject("global/path/item1", null, "VALOR 1");
		objectsManager.registerObject("global/path/type/item1", null, "VALOR 2");
		objectsManager.registerObject("global/path/item2", null, "VALOR 3");
		objectsManager.registerObject("global/path/type/item2_val", null, "VALOR 4");
		
		List<Object> list = objectsManager.list("global/path", "item1", null, true);
		
		assertTrue(list.size() == 2);
		assertTrue(list.indexOf("VALOR 1") != -1);
		assertTrue(list.indexOf("VALOR 2") != -1);
	}

	@Test
	public void testListLocale() {
		objectsManager.registerObject("global/path/item1", null, "VALOR 1");
		objectsManager.registerObject("global/path/type/item1", new Locale("pt","BR"), "VALOR 2");
		objectsManager.registerObject("global/path/item2", null, "VALOR 3");
		objectsManager.registerObject("global/path/type/item2_val", new Locale("pt","BR"), "VALOR 4");
		
		List<Object> list = objectsManager.list("global/path", null, new Locale("pt","BR"), true);
		
		assertTrue(list.size() == 2);
		assertTrue(list.indexOf("VALOR 2") != -1);
		assertTrue(list.indexOf("VALOR 4") != -1);
	}

	@Test
	public void testListLocaleANDNoRecursive() {
		objectsManager.registerObject("global/path/item1", null, "VALOR 1");
		objectsManager.registerObject("global/path/type/item1", new Locale("pt","BR"), "VALOR 2");
		objectsManager.registerObject("global/path/type/item2", new Locale("pt","BR"), "VALOR 3");
		objectsManager.registerObject("global/path/item2", null, "VALOR 4");
		objectsManager.registerObject("global/path/type/path2/item2_val", new Locale("pt","BR"), "VALOR 5");
		
		List<Object> list = objectsManager.list("global/path/type", null, new Locale("pt","BR"), false);
		
		assertTrue(list.size() == 2);
		assertTrue(list.indexOf("VALOR 2") != -1);
		assertTrue(list.indexOf("VALOR 3") != -1);
	}

	@Test
	public void testListLocaleANDNoRecursiveANDName() {
		objectsManager.registerObject("global/path/item1", null, "VALOR 1");
		objectsManager.registerObject("global/path/type/item1", new Locale("pt","BR"), "VALOR 2");
		objectsManager.registerObject("global/path/type/item2", new Locale("pt","BR"), "VALOR 3");
		objectsManager.registerObject("global/path/item2", null, "VALOR 4");
		objectsManager.registerObject("global/path/type/path2/item2_val", new Locale("pt","BR"), "VALOR 5");
		
		List<Object> list = objectsManager.list("global/path/type", "2", new Locale("pt","BR"), false);
		
		assertTrue(list.size() == 1);
		assertTrue(list.indexOf("VALOR 3") != -1);
	}

	@Test
	public void testListObject() {
		objectsManager.registerObject("global/path/item1", null, "VALOR 1");
		objectsManager.registerObject("global/path/type/item1", null, "VALOR 2");
		objectsManager.registerObject("global/path/type/item1", new Locale("pt","BR"), "VALOR 3");
		objectsManager.registerObject("global/path/type/item2", null, "VALOR 4");
		objectsManager.registerObject("global/path/item2", null, "VALOR 5");
		objectsManager.registerObject("global/path/type/path2/item2_val", new Locale("pt","BR"), "VALOR 6");
		
		List<ObjectEntry> list = objectsManager.listObjects("global/path/type", null, false);
		
		assertTrue(list.size() == 2);
		
		assertEquals("/path/type/item1",list.get(0).getFullId());
		assertEquals("item1",list.get(0).getId());
		assertEquals("/path/type",list.get(0).getPath());
		assertEquals("VALOR 2",list.get(0).getObject());
		assertEquals("VALOR 3",list.get(0).getObject(new Locale("pt", "BR")));

		assertEquals("/path/type/item2",list.get(1).getFullId());
		assertEquals("item2",list.get(1).getId());
		assertEquals("/path/type",list.get(1).getPath());
		assertEquals("VALOR 4",list.get(1).getObject());
		assertNull(list.get(1).getObject(new Locale("pt", "BR")));
		
	}
	
}
