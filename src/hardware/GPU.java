package hardware;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import screen.Display;

public class GPU {
	
	int [] _vram = new int[8192];
	int [] _oam = new int[160];
	int [] _reg;
	int [] _tilemap;
	int [] _objdata;
	int [] _objdatasorted;
	
	final int width = 160 * 2;
	final int height = 144 * 2;
	int _mode = 0;
	int _modeClock = 0;
	int _line = 0;
	private Z80 _z80;
	public class Palette{
		int [] bg = new int[4];
		int [] obj0 = new int[4];
		int [] obj1 = new int[4];
	}
	private Display frame;
	public Palette _palete;
	private BufferedImage _scrn;

	public void updatetile(int i, int val) {
		// TODO Auto-generated method stub
		
	}

	public void updateoam(int addr, int val) {
		// TODO Auto-generated method stub
		
	}
	
	public Image getFrame(){
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		
		
		
		return image;
		
		
	}

	public void wb(int addr, int val) {
		// TODO Auto-generated method stub
		
	}

	public void setp() {
		// TODO Auto-generated method stub
		this._modeClock += this._z80._r._t;
		//state machine;
		switch(this._mode){
		case 2:
			//OAM read mode, scanline active
			if(this._modeClock >= 80){
				this._modeClock = 0;
				this._mode = 3;
			}
			break;
		case 3:
			//VRAM read mode, scanline active
			//treat end of mode 3 as end of scanline
			if(this._modeClock >= 172){
				this._modeClock = 0;
				this._mode = 0;
				//write a scanline to the frame buffer 
				this.renderscan();
			}
			break;
		case 0:
			//Hblank
			//after the last hblank, push the screen data to screen
			if(this._modeClock >= 204){
				this._modeClock = 0;
				this._line ++;
				if(this._line == 143){
					//enter vblank
					this._mode = 1;
					this.frame.putImageData(this._scrn,0,0);
				}
				else{
					this._mode = 2;
				}
			}
			break;
		case 1:
			if(this._modeClock >= 456){
				this._modeClock = 0;
				this._line ++;
				if(this._line > 153){
					this._mode =2;
					this._line = 0;
				}
			}break;
			
		}
	}

	private void renderscan() {
		// TODO Auto-generated method stub
		
	}
	
}
