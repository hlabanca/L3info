/* Quartus II Version 5.0 Build 148 04/26/2005 SJ Web Edition */
JedecChain;
	FileRevision(JESD32A);
	DefaultMfr(6E);

	P ActionCode(Cfg)
		Device PartName(EPF10K10L84) Path("") File("fpga1.sof") MfrSpec(OpMask(1));
	P ActionCode(Cfg)
		Device PartName(EPF10K10L84) Path("") File("fpga2vide.sof") MfrSpec(OpMask(1));

ChainEnd;

AlteraBegin;
	ChainType(JTAG);
AlteraEnd;
