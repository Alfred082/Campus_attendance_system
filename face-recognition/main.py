import face_recognition
import cv2
import sys

# 首先提高被识别图片的清晰度，以便提高识别率
def recongnition(image,face):
    image = cv2.imread(image)
    small_frame = cv2.resize(image, (0, 0), fx=0.25, fy=0.25)
    image = small_frame[:, :, ::-1]  # 将opencv的BGR格式转换为RGB格式
    face_locations = face_recognition.face_locations(image)
    image_encoding = face_recognition.face_encodings(image, face_locations)
    if len(image_encoding) < 1 :
        return 3
    else:
        img2 = image_encoding[0]


    # 加载考勤的人脸图片
    face_image = face_recognition.load_image_file(face)
    face_locations = face_recognition.face_locations(face_image)
    face_encoding = face_recognition.face_encodings(face_image, face_locations)
    if len(face_encoding) < 1 :
        return 3
    else:
        img1 = face_encoding[0]
    face_encodings = []
    face_encodings.append(img1)

    # 执行人脸识别
    matches = face_recognition.compare_faces(face_encodings, img2, tolerance=0.49)
    if True in matches:
        return 2
    return 1

if __name__ == '__main__':


    # path1 = "F:\\NIITProject\\project-interface\\src\\main\\resources\\static\\check\\13_55.png"
    # path2 = "F:\\NIITProject\\project-interface\\src\\main\\resources\\static\\face\\13.png"

    path1 = sys.argv[1]
    path2 = sys.argv[2]

    result = recongnition(path1, path2)
    print(result)
