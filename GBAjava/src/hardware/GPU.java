package hardware;

public class GPU {
	int [] _vram = new int[8192];
	int [] _oam = new int[160];
	
	public class Palette{
		int [] bg = new int[4];
		int [] obj0 = new int[4];
		int [] obj1 = new int[4];
	}
	
	public Palette _palete;
	
}
