package hardware;

public class MMU {

	public class MBC{
		private int rombank;
		private int rambank;
		private int ramon;
		private int mode;
		
		public MBC(){
			this.rambank = 0;
			this.rombank = 0;
			this.ramon = 0;
			this.mode = 0;
		}
	
	}
	private Z80 _z80;
	private GPU _gpu;
	private int [] _bios;
	final int wsize = 8192;
	final int esize = 32768;
	final int zsize = 127;
	private String rom;
	private int _carttype;
	private MBC _mbc;
	private int _romoffs;
	private int _ramoffs;
	private int [] _eram;
	private int [] _wram;
	private int [] _zram;
	private int _inbios = 1;
	private int _ie = 0;
	private int _if = 0;
	private KEY _key;
	public MMU(){
		//init it
		this.set_bios(new int []{
				0x31, 0xFE, 0xFF, 0xAF, 0x21, 0xFF, 0x9F, 0x32, 0xCB, 0x7C, 0x20, 0xFB, 0x21, 0x26, 0xFF, 0x0E,
			    0x11, 0x3E, 0x80, 0x32, 0xE2, 0x0C, 0x3E, 0xF3, 0xE2, 0x32, 0x3E, 0x77, 0x77, 0x3E, 0xFC, 0xE0,
			    0x47, 0x11, 0x04, 0x01, 0x21, 0x10, 0x80, 0x1A, 0xCD, 0x95, 0x00, 0xCD, 0x96, 0x00, 0x13, 0x7B,
			    0xFE, 0x34, 0x20, 0xF3, 0x11, 0xD8, 0x00, 0x06, 0x08, 0x1A, 0x13, 0x22, 0x23, 0x05, 0x20, 0xF9,
			    0x3E, 0x19, 0xEA, 0x10, 0x99, 0x21, 0x2F, 0x99, 0x0E, 0x0C, 0x3D, 0x28, 0x08, 0x32, 0x0D, 0x20,
			    0xF9, 0x2E, 0x0F, 0x18, 0xF3, 0x67, 0x3E, 0x64, 0x57, 0xE0, 0x42, 0x3E, 0x91, 0xE0, 0x40, 0x04,
			    0x1E, 0x02, 0x0E, 0x0C, 0xF0, 0x44, 0xFE, 0x90, 0x20, 0xFA, 0x0D, 0x20, 0xF7, 0x1D, 0x20, 0xF2,
			    0x0E, 0x13, 0x24, 0x7C, 0x1E, 0x83, 0xFE, 0x62, 0x28, 0x06, 0x1E, 0xC1, 0xFE, 0x64, 0x20, 0x06,
			    0x7B, 0xE2, 0x0C, 0x3E, 0x87, 0xF2, 0xF0, 0x42, 0x90, 0xE0, 0x42, 0x15, 0x20, 0xD2, 0x05, 0x20,
			    0x4F, 0x16, 0x20, 0x18, 0xCB, 0x4F, 0x06, 0x04, 0xC5, 0xCB, 0x11, 0x17, 0xC1, 0xCB, 0x11, 0x17,
			    0x05, 0x20, 0xF5, 0x22, 0x23, 0x22, 0x23, 0xC9, 0xCE, 0xED, 0x66, 0x66, 0xCC, 0x0D, 0x00, 0x0B,
			    0x03, 0x73, 0x00, 0x83, 0x00, 0x0C, 0x00, 0x0D, 0x00, 0x08, 0x11, 0x1F, 0x88, 0x89, 0x00, 0x0E,
			    0xDC, 0xCC, 0x6E, 0xE6, 0xDD, 0xDD, 0xD9, 0x99, 0xBB, 0xBB, 0x67, 0x63, 0x6E, 0x0E, 0xEC, 0xCC,
			    0xDD, 0xDC, 0x99, 0x9F, 0xBB, 0xB9, 0x33, 0x3E, 0x3c, 0x42, 0xB9, 0xA5, 0xB9, 0xA5, 0x42, 0x4C,
			    0x21, 0x04, 0x01, 0x11, 0xA8, 0x00, 0x1A, 0x13, 0xBE, 0x20, 0xFE, 0x23, 0x7D, 0xFE, 0x34, 0x20,
			    0xF5, 0x06, 0x19, 0x78, 0x86, 0x23, 0x05, 0x20, 0xFB, 0x86, 0x20, 0xFE, 0x3E, 0x01, 0xE0, 0x50
		});
		this.rom = "";
		this._carttype = 0;
		this._romoffs = 0x4000;
		this._ramoffs = 0;
		
	}
	

	public int [] get_bios() {
		return _bios;
	}

	public void set_bios(int [] _bios) {
		this._bios = _bios;
	}
	
	//read byte
	public int rb(int addr){
		switch(addr & 0xF000){
		//if addr less than 1000;
		case 0x0000:
			//if in bios mode;
			if(this._inbios != 0){
				if(addr < 0x0100) {
					return this._bios[addr];
				}
				else if(this._z80._r.pc == 0x0100){
					this._inbios = 0;
				}
				else{
					return this.rom.charAt(addr);
				}
			}
			break;
			
		case 0x1000:
			return this.rom.charAt(addr);
		case 0x2000:
			return this.rom.charAt(addr);	
		case 0x3000:
			return this.rom.charAt(addr);
			
		case 0x4000:
			return this.rom.charAt(this._romoffs + (addr & 0x3ff));
		case 0x5000:
			return this.rom.charAt(this._romoffs + (addr & 0x3ff));
		case 0x6000:
			return this.rom.charAt(this._romoffs + (addr & 0x3ff));
		case 0x7000:
			return this.rom.charAt(this._romoffs + (addr & 0x3ff));
			
		case 0x8000:
			return this._gpu._vram[addr&0x1FFF];
		case 0x9000:
			return this._gpu._vram[addr&0x1FFF];

		case 0xA000:
			return this._eram[this._ramoffs+(addr&0x1FFF)];
		case 0xB000:
			return this._eram[this._ramoffs+(addr&0x1FFF)];
		case 0xC000:
		case 0xD000:
		case 0xE000:
			return this._wram[addr&0x1FFF];
		case 0xF000:
			switch(addr &0x0F00){
			case 0xE00:
				return ( (addr&0xFF) < 0xA0)? this._gpu._oam[addr&0xFF] : 0 ;
				
			case 0xF00:
			{
				if(addr == 0xFFFF){
					return this._ie;
				}
				else if(addr > 0xFF7F){
					return this._zram[addr & 0x7F];
				}
				else switch(addr&0xF0){
				case 0x00:
					switch (addr & 0xF){
					case 0: return this._key.rb();
					}
				}
			}
			default:
				return this._wram[addr & 0x1FFF];
			}
		}
		
		return 0;
		
	}
	public void wb(int addr, int val){
		;
	}
	//read word
	public int rw(int addr){
		return 0;
	}
	public void ww(int addr, int val){
		;
	}
}
