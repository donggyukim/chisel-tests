gendir  := generated
tutdir  := tutorial/examples
designs := $(filter-out examples Image Sound,\
           $(notdir $(basename $(wildcard $(tutdir)/*.scala))))
VPATH   := $(tutdir):$(generated)

C_FLAGS  := --targetDir $(gendir) --genHarness --compile --test --vcd --vcdMem --isVCDinline --debug 
V_FLAGS  := $(C_FLAGS) --v

all : cpp v
cpp : $(addsuffix .cpp, $(designs))
v   : $(addsuffix .v,   $(designs))

%.cpp: %.scala 
	mkdir -p $(gendir)
	sbt "run $(basename $@) $(C_FLAGS)" | tee $@.out

%.v: %.scala 
	mkdir -p $(gendir)
	sbt "run $(basename $@) $(V_FLAGS)" | tee $@.out

clean:
	rm -rf $(gendir) *.out

cleanall:
	rm -rf project/target target
	$(MAKE) -C chisel clean	

.PHONY: all cpp v clean cleanall
