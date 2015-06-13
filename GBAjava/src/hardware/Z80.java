package hardware;

public class Z80 {
	//clock structure;
	public class _Clock{
		public _Clock(){
			this._init();
		}
		
		public void _init(){
			this._m = 0;
			this._t = 0;
		}
		private int _m;
		private int _t;
		public int getM() {
			return _m;
		}
		public void setM(int m) {
			this._m = m;
		}
		
		public int getT() {
			return _t;
		}
		
		public void setT(int t) {
			this._t = t;
		}
	};
	//register structure;
	public class _R{
		
		public _R(){
			this._init();
		}
		
		public void _init(){
			this._a = 0;
			this._b = 0;
			this._c = 0;
			this._d = 0;
			this._e = 0;
			this._f = 0;
			this._h = 0;
			this._l = 0;
			this.pc = 0;
			this.sp = 0;
			this._m = 0;
			this._t = 0;
			this.i = 0;
			this.ime = 0;
			this.r = 0;
		}
		
		private int _a;
		private int _b;
		private int _c;
		private int _d;
		private int _e;
		private int _f;
		private int _h;
		private int _l;
		
		private int pc;
		private int sp;
		private int i;
		private int r;
		
		
		private int _m;
		private int ime;
		private int _t;
		
	};
	
	//register instance;
	private _R _r;
	//clock instance;
	private _Clock _clock;
	
	private int _halt;
	private int _stop;
	/**
	 * Constructor
	 */
	public Z80(){
		this._clock = new _Clock();
		this._r = new _R();
		this._halt = 0;
		this._stop = 0;
	}
	/**
	 * reset clock and register
	 */
	public void Reset(){
		this._clock._init();
		this._r._init();
		this._halt = 0;
		this._stop = 0;
		this._r.ime = 1;
		
	}
	
	/***
	 * regions
	 * 
	 * 
	 */
	/**
	 * register B Load
	 * @param other load from where
	 */
	public void LDrr_b(char other){
		switch(other){
		case 'b':
			this._r._b = this._r._b;
			break;
		case 'c':
			this._r._b = this._r._c;
			break;
		case 'd':
			this._r._b = this._r._d;
			break;
		case 'e':
			this._r._b = this._r._e;
			break;
		case 'h':
			this._r._b = this._r._h;
			break;
		case 'l':
			this._r._b = this._r._l;
			break;
		case 'a':
			this._r._b = this._r._a;
			break;
		default:
			break;			
		}		
		this._r._m = 1;
	}
	public void LDrr_bb(){
		LDrr_b('b');
	}

	public void LDrr_bc(){
		LDrr_b('c');
	}

	public void LDrr_bd(){
		LDrr_b('d');
	}

	public void LDrr_be(){
		LDrr_b('e');
	}

	public void LDrr_bh(){
		LDrr_b('h');
	}

	public void LDrr_bl(){
		LDrr_b('l');
	}
	public void LDrr_ba(){
		LDrr_b('a');
	}
	
	
	public void LDrr_c(char other){
		switch(other){
		case 'b':
			this._r._c = this._r._b;
			break;
		case 'c':
			this._r._c = this._r._c;
			break;
		case 'd':
			this._r._c = this._r._d;
			break;
		case 'e':
			this._r._c = this._r._e;
			break;
		case 'h':
			this._r._c = this._r._h;
			break;
		case 'l':
			this._r._c = this._r._l;
			break;
		case 'a':
			this._r._c = this._r._a;
			break;
		default:
			break;			
		}		
		this._r._m = 1;
	}

	public void LDrr_cb(){
		LDrr_c('b');
	}

	public void LDrr_cc(){
		LDrr_c('c');
	}

	public void LDrr_cd(){
		LDrr_c('d');
	}

	public void LDrr_ce(){
		LDrr_c('e');
	}

	public void LDrr_ch(){
		LDrr_c('h');
	}

	public void LDrr_cl(){
		LDrr_c('l');
	}
	public void LDrr_ca(){
		LDrr_c('a');
	}
	
	/**
	 * Load register d.
	 */

	public void LDrr_d(char other){
		switch(other){
		case 'b':
			this._r._d = this._r._b;
			break;
		case 'c':
			this._r._d = this._r._c;
			break;
		case 'd':
			this._r._d = this._r._d;
			break;
		case 'e':
			this._r._d = this._r._e;
			break;
		case 'h':
			this._r._d = this._r._h;
			break;
		case 'l':
			this._r._d = this._r._l;
			break;
		case 'a':
			this._r._d = this._r._a;
			break;
		default:
			break;			
		}		
		this._r._m = 1;
	}

	public void LDrr_db(){
		LDrr_d('b');
	}

	public void LDrr_dc(){
		LDrr_d('c');
	}

	public void LDrr_dd(){
		LDrr_d('d');
	}

	public void LDrr_de(){
		LDrr_d('e');
	}

	public void LDrr_dh(){
		LDrr_d('h');
	}

	public void LDrr_dl(){
		LDrr_d('l');
	}
	public void LDrr_da(){
		LDrr_d('a');
	}
	

	/**
	 * Load register e.
	 */

	public void LDrr_e(char other){
		switch(other){
		case 'b':
			this._r._e = this._r._b;
			break;
		case 'c':
			this._r._e = this._r._c;
			break;
		case 'd':
			this._r._e = this._r._d;
			break;
		case 'e':
			this._r._e = this._r._e;
			break;
		case 'h':
			this._r._e = this._r._h;
			break;
		case 'l':
			this._r._e = this._r._l;
			break;
		case 'a':
			this._r._e = this._r._a;
			break;
		default:
			break;			
		}		
		this._r._m = 1;
	}

	public void LDrr_eb(){
		LDrr_e('b');
	}

	public void LDrr_ec(){
		LDrr_e('c');
	}

	public void LDrr_ed(){
		LDrr_e('d');
	}

	public void LDrr_ee(){
		LDrr_e('e');
	}

	public void LDrr_eh(){
		LDrr_e('h');
	}

	public void LDrr_el(){
		LDrr_e('l');
	}
	public void LDrr_ea(){
		LDrr_e('a');
	}
	

	/**
	 * Load register h.
	 */

	public void LDrr_h(char other){
		switch(other){
		case 'b':
			this._r._h = this._r._b;
			break;
		case 'c':
			this._r._h = this._r._c;
			break;
		case 'd':
			this._r._h = this._r._d;
			break;
		case 'e':
			this._r._h = this._r._e;
			break;
		case 'h':
			this._r._h = this._r._h;
			break;
		case 'l':
			this._r._h = this._r._l;
			break;
		case 'a':
			this._r._h = this._r._a;
			break;
		default:
			break;			
		}		
		this._r._m = 1;
	}

	public void LDrr_hb(){
		LDrr_h('b');
	}

	public void LDrr_hc(){
		LDrr_h('c');
	}

	public void LDrr_hd(){
		LDrr_h('d');
	}

	public void LDrr_he(){
		LDrr_h('e');
	}

	public void LDrr_hh(){
		LDrr_h('h');
	}

	public void LDrr_hl(){
		LDrr_h('l');
	}
	public void LDrr_ha(){
		LDrr_h('a');
	}
	
	

	/**
	 * Load register a.
	 */

	public void LDrr_a(char other){
		switch(other){
		case 'b':
			this._r._a = this._r._b;
			break;
		case 'c':
			this._r._a = this._r._c;
			break;
		case 'd':
			this._r._a = this._r._d;
			break;
		case 'e':
			this._r._a = this._r._e;
			break;
		case 'h':
			this._r._a = this._r._h;
			break;
		case 'l':
			this._r._a = this._r._l;
			break;
		case 'a':
			this._r._a = this._r._a;
			break;
		default:
			break;			
		}		
		this._r._m = 1;
	}

	public void LDrr_ab(){
		LDrr_a('b');
	}

	public void LDrr_ac(){
		LDrr_a('c');
	}

	public void LDrr_ad(){
		LDrr_a('d');
	}

	public void LDrr_ae(){
		LDrr_a('e');
	}

	public void LDrr_ah(){
		LDrr_a('h');
	}

	public void LDrr_al(){
		LDrr_a('l');
	}
	public void LDrr_aa(){
		LDrr_a('a');
	}
	
	
	
	

	/**
	 * Load register l.
	 */

	public void LDrr_l(char other){
		switch(other){
		case 'b':
			this._r._l = this._r._b;
			break;
		case 'c':
			this._r._l = this._r._c;
			break;
		case 'd':
			this._r._l = this._r._d;
			break;
		case 'e':
			this._r._l = this._r._e;
			break;
		case 'h':
			this._r._l = this._r._h;
			break;
		case 'l':
			this._r._l = this._r._l;
			break;
		case 'a':
			this._r._l = this._r._a;
			break;
		default:
			break;			
		}		
		this._r._m = 1;
	}

	public void LDrr_lb(){
		LDrr_l('b');
	}

	public void LDrr_lc(){
		LDrr_l('c');
	}

	public void LDrr_ld(){
		LDrr_l('d');
	}

	public void LDrr_le(){
		LDrr_l('e');
	}

	public void LDrr_lh(){
		LDrr_l('h');
	}

	public void LDrr_ll(){
		LDrr_l('l');
	}
	public void LDrr_la(){
		LDrr_l('a');
	}
	
	
	/**
	 * Add E to A, leave result in A
	 * Add A, E
	 */
	public void ADDr_e(){
		//perform
		this._r._a += this._r._e;
		//clear flag
		this._r._f = 0;
		//check for zero;
		if((this._r._a & 255) == 0){
			this._r._f |= 0x80;
		}
		if(this._r._a > 255){
			this._r._f |= 0x10;
		}
		//leave things behind 255;
		this._r._a &=255;
		//taken 1-M time
		this._r._m = 1;
		this._r._t = 4;
		
	}
	
}
