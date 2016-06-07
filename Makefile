gendir  := generated
tutdir  := tutorial/examples
designs := $(filter-out examples Image Sound Darken,\
           $(notdir $(basename $(wildcard $(tutdir)/*.scala))))
VPATH   := $(tutdir):$(generated)
FLAGS   := --targetDir $(gendir) --v --genHarness --compile --test
#--noPropagation

all : cpp v
cpp : $(addsuffix .cpp, $(addprefix V, $(designs)))
v   : $(addsuffix .v,   $(designs))

V%.cpp: %.scala 
	mkdir -p $(gendir)
	sbt "run $* $(FLAGS)" | tee $@.out

%.v: %.scala 
	mkdir -p $(gendir)
	sbt "run $* $(FLAGS) --vcs" | tee $@.out

clean:
	rm -rf $(gendir) *.out

cleanall:
	rm -rf project/target target
	$(MAKE) -C chisel clean	

.PHONY: all cpp v clean cleanall
