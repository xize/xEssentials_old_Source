/*
 FileManager (experimental) api written by Xeph0re(xize) © 2013
 hold single instances of files in one method, in order to keep less objects into the jvm garbage collector and makes less
 constructs depending if its only used for configuration files, the older version of xEssentials contains a
 wild spread of constructs and new file instances which I believe is wrong for memory and cpu usage
 when the limit size in gc has been overheat.
 */

package tv.mineinthebox;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import tv.mineinthebox.xEssentials;


public class fileManager extends xEssentials {
	
	public static String getDir() {
		return xEssentials.getFileFolder();
	}
	
	public static boolean writeFile(String configFileName, String path, String value, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			} else {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean writeFile(String configFileName, String path, Object value, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			} else {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isDirectory(String fileLocation) {
		try {
			File f = new File(fileLocation);
			if(f.isDirectory()) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static File[] getFileList(String Folder) {
		try {
			File dir = new File(Folder);
			if(dir.isDirectory()) {
				File[] list = dir.listFiles();
				return list;	
			}	
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static File returnFile(String configFileName, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				return f;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Map<?, ?>> getMapList(String configFileName, String path, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				return con.getMapList(path);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean writeFile(String configFileName, String path, Boolean value, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			} else {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean writeFile(String configFileName, String path, ArrayList<String> value, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			} else {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean writeFile(String configFileName, String path, ItemStack[] value, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			} else {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean writeFile(String configFileName, String path, Date value, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			} else {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean writeFile(String configFileName, String path, Long value, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			} else {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean writeFile(String configFileName, String path, Double value, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			} else {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean writeFile(String configFileName, String path, Integer value, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			} else {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean writeFile(String configFileName, String path, Float value, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			} else {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set(path, value);
				con.save(f);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isSet(String configFileName, String path, String fileLocation) {
		try {
			 File f = new File(fileLocation + File.separator + configFileName);
			 if(f.exists()) {
				 FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				 if(con.isSet(path)) {
					 return true;
				 } else {
					 return false;
				 }
			 } else {
				 return false;
			 }
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean getBooleanValue(String configFileName, String path, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.isSet(path)) {
					if(con.getBoolean(path)) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String getStringValue(String configFileName, String path, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.isSet(path)) {
					if(con.isString(path)) {
						return con.getString(path);
					} else {
						return null;
					}
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Double getDoubleValue(String configFileName, String path, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				return con.getDouble(path);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<?> getListValue(String configFileName, String path, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				return con.getList(path);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<String> getStringListValue(String configFileName, String path, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				return con.getStringList(path);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Integer getIntegerValue(String configFileName, String path, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				return con.getInt(path);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Long getLongValue(String configFileName, String path, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				return con.getLong(path);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Float getFloatValue(String configFileName, String path, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				return Float.valueOf((String) con.get(path));
			} else {
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean file_exists(String configFileName, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + configFileName);
			if(f.exists()) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean createHeader(String fileName, String header, String fileLocation) {
		try {
			File f = new File(fileLocation + File.separator + fileName);
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				FileConfigurationOptions opt = con.options();
				opt.header(header);
				con.save(f);
			} else {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				FileConfigurationOptions opt = con.options();
				opt.header(header);
				con.save(f);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
