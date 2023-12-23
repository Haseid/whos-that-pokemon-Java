import cv2
import sys
import numpy as np

#for img_path in sys.argv[1:]:
for i in  range(1,722):
	img_path = str(i)+".png" 
	new_path = img_path.replace(".png",".png")

	img = cv2.imread(img_path, cv2.IMREAD_UNCHANGED)
	img[img[:,:, 3] != 0] = (0, 0, 0, 255)
	cv2.imwrite(new_path, img)
	print(i)