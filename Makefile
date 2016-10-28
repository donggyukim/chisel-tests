base_dir = $(abspath .)

SBT ?= sbt
SBT_FLAGS ?=

publishLocal:
	cd $(base_dir)/firrtl && $(SBT) $(SBT_FLAGS) publishLocal
	cd $(base_dir)/chisel && $(SBT) $(SBT_FLAGS) publishLocal
	cd $(base_dir)/interp && $(SBT) $(SBT_FLAGS) publishLocal
	cd $(base_dir)/testers && $(SBT) $(SBT_FLAGS) publishLocal
