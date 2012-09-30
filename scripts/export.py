# PlainsWalker exporting script
# to be used as a plugin for SoftImage

# the file is of the format: e x y z i j k n
# e = identifier for the animal
# x y z = position
# i j k = direction
# n = frame number

# note: for now, this export ignored the direction/heading of the animal

animal_fcurves = {}

filename = raw_input("Please enter the simulation filename:\n")

with open(str(filename), 'r') as f:
	# loop through and parse lines
	for line in f:
		line = line[:-1]
		tokens = line.split(" ")
		
		# if this animal id is already in the dictionary
		if tokens[0] in animal_fcurves.keys():
			# append its x-fcurve
			animal_fcurves[tokens[0]]['x'].append(tokens[7])
			animal_fcurves[tokens[0]]['x'].append(tokens[1])
			# append its y-fcurve
			animal_fcurves[tokens[0]]['y'].append(tokens[7])
			animal_fcurves[tokens[0]]['y'].append(tokens[2])
			# append its z-fcurve
			animal_fcurves[tokens[0]]['z'].append(tokens[7])
			animal_fcurves[tokens[0]]['z'].append(tokens[3])
		
		# if the animal is not in the dictionary	
		else:
			animal_fcurves[tokens[0]] = {'x':[], 'y':[], 'z':[]}
			# append its x-fcurve
			animal_fcurves[tokens[0]]['x'].append(tokens[7])
			animal_fcurves[tokens[0]]['x'].append(tokens[1])
			# append its y-fcurve
			animal_fcurves[tokens[0]]['y'].append(tokens[7])
			animal_fcurves[tokens[0]]['y'].append(tokens[2])
			# append its z-fcurve
			animal_fcurves[tokens[0]]['z'].append(tokens[7])
			animal_fcurves[tokens[0]]['z'].append(tokens[3])

# this is possibly slightly hacked: now we write to file the commands in a python script to render this all in SoftImage
with open("import.py", 'w') as f:
	for key in animal_fcurves.keys():
		f.write(key + " = Application.ActiveSceneRoot.addPrimitive('Sphere','NurbsSurface')\n")
		f.write(key+".PosX.addFCurve2([")
		# loop through x values and print
		for val in range(len(animal_fcurves[key]['x'])):
			f.write(animal_fcurves[key]['x'][val])
			if val<len(animal_fcurves[key]['x'])-1:
				f.write(", ")
		f.write("])\n")
		f.write(key+".PosY.addFCurve2([")
		# loop through y values and print
		for val in range(len(animal_fcurves[key]['y'])):
			f.write(animal_fcurves[key]['y'][val])
			if val<len(animal_fcurves[key]['y'])-1:
				f.write(", ")
		f.write("])\n")
		f.write(key+".PosZ.addFCurve2([")
		# loop through z values and print
		for val in range(len(animal_fcurves[key]['z'])):
			f.write(animal_fcurves[key]['z'][val])
			if val<len(animal_fcurves[key]['z'])-1:
				f.write(", ")
		f.write("])\n")





