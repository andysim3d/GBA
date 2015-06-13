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
	
	public void LDrr_bb(){
		this._r._b = this._r._b;
		this._r._m = 1;
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
