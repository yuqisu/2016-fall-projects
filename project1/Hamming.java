����   4 �
  � � �
 7 � � �
 7 � �
 7 � � �
 7 � �
  � �
  � �
  � � �
 � �
  �
  �
  � �
  �
  � �
  � �
  �	 7 �
 7 �
  �
 � �
  �
  �
  �
  �
  �	 7 � �
  �	 7 �
  �
 7 �
 � �
 � �	 7 �	 7 � �	 7 � � y � encode74 [[I errorChecking7_4 decode74 
encode1511 errorChecking15_11 
decode1511 <init> ()V Code LineNumberTable LocalVariableTable this 	LHamming; main ([Ljava/lang/String;)V args [Ljava/lang/String; 
Exceptions � 
encode_7_4 '(Ljava/lang/String;Ljava/lang/String;)V mid I parts s1 Ljava/lang/String; s2 c i 
inFileName outFileName in Ljava/io/FileInputStream; out Ljava/io/FileOutputStream; StackMapTable � � � � matrixMultiply )(Ljava/lang/String;[[I)Ljava/lang/String; j data matrix bytes [C code q 9 g 
decode_7_4 	dataSplit 
correctBit newI newCode check count I 	flipIndex (Ljava/lang/String;)I a sum divide [I encode_15_11 decode_15_11 error 	parityBit <clinit> 
SourceFile Hamming.java ? @ wiki.txt sample_7_4out.txt L M wiki_7_4_flip.txt sample_7_4decode.txt l M sample_15_11out.txt z M wiki_15_11_flip.txt sample_15_11decode.txt { M java/io/FileInputStream ? � java/io/FileOutputStream   � � %8s java/lang/Object � � � � � � � � � java/lang/String � � � � java/lang/StringBuilder 0 � � 8 9 a b � � � � � � � @ � � � � : 9 000 � � ; 9 � � t u � u � � < 9 = 9 0000 > 9 1 Hamming java/io/IOException java/lang/Throwable (Ljava/lang/String;)V read ()I java/lang/Integer toBinaryString (I)Ljava/lang/String; format 9(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String; replace (CC)Ljava/lang/String; length 	substring (II)Ljava/lang/String; append -(Ljava/lang/String;)Ljava/lang/StringBuilder; toString ()Ljava/lang/String; parseInt (Ljava/lang/String;I)I write (I)V close toCharArray ()[C (I)Ljava/lang/StringBuilder; equals (Ljava/lang/Object;)Z split '(Ljava/lang/String;)[Ljava/lang/String; ! 7      8 9    : 9    ; 9    < 9    = 9    > 9   	  ? @  A   /     *� �    B        C        D E   	 F G  A   W     � � � 	
� �    B       #  $  %  &  * C        H I   J     K 	 L M  A  "     �MN� Y*� M� Y+� N:,� Y6� �� Y� S�  0� :� l6� Y� SY� S:� Y� � 2�  � !� � ":� Y� � 2�  � !� � ":	-� #� $-	� #� $��g,� ,� %-� -� &� :
,� ,� %-� -� &
��   � �   � � �    B   ^    -  .  1  2  4  6 % 8 ? 9 H : c < � > � A � B � E � G � H � J � K � G � H � J � K � O C   f 
 H k N O  c P P I  � 2 Q R  �  S R 	 ! � T O   � U R    � V R     � W R   � X Y   � Z [  \   Q �   ] ] ^ _  ]  � �J `� 	  ] ] ^ _       `  �   ] ] ^ _   J     K 	 a b  A       [*� 'MN66+�� G6,�� +2.,4h`6����p6� Y� -� � (� "N6����-�    B   2    R  S  T  U  V  W 0 V 6 Y < Z P [ S U Y ] C   H    c O   K U O    [ d R     [ e 9   V f g   S h R   P i O  \   ! �   ] j k ]  � 	� � " 	 l M  A  1    <MN� Y*� M� Y+� N:::6,� Y6� �� Y� S�  0� :� :� )� !:*� +� "� Y� � � ,� !� � ":� v� -:		� .d2� /�6
	� .d
� 0S:6	�� "� Y� � 	2� � ":���ܻ Y� � � ,� !� � ":p��&-� #� $:��,� ,� %-� -� &� :,� ,� %-� -� &��  &  &(&    B   � #   b  c  f  g  i  j  k " l % n 0 o 3 p M q U s _ u i v � x � y � z � | � } � ~ � } � � � � � � � � � �& �, �0 �4 �; � C   �  � ' c O  � j m I 	 � Y n O 
 � F o R  , � T O   � U R   � p R  " � q R  % � r O   < V R    < W R  : X Y  8 Z [  \   � � % 	 ] ] ^ _  ] ] ]  � b 	 ] ] ^ _ ] ] ]  � /  ] ] ^ _ ] ] ] s ]  � &� �   ] ] ^ _  J `� 	  ] ] ^ _          `  �   ] ] ^ _   J     K 	 t u  A   �     I<*� -M,��
N6,�� -,2� /O����-.� `<-.� `<-.� `<�    B   6    �  � 	 �  �  � # � ) � / � 3 � 9 � = � C � G � C   4    U O    I v R    G w O  	 @ x I   ; r y  \    �   ] s 6  � 			 	 z M  A  /     �MN� Y*� M� Y+� N:,� Y6� �� Y� *� � Y� S�  0� � � ":� Y� � � 1� !� � ":� l6� Y� SY� S:2:	2:
-	� #� $-
� #� $��i,� ,� %-� -� &� :,� ,� %-� -� &��   � �   � � �    B   b    �  �  �  �  �  � % � Q � m � v � � � � � � � � � � � � � � � � � � � � � � � � � � � � � C   p  m D p R  v ; N O  �   P I  �  Q R 	 �  S R 
 ! � T O   � U R    � V R     � W R   � X Y   � Z [  \   R �   ] ] ^ _  ]  � �J `� 	  ] ] ^ _        `  �   ] ] ^ _   J     K 	 { M  A  )    �MN� Y*� M� Y+� N:::6,� Y6�v�� Y� � � Y� S�  0� � � ":p��» Y� � � � � ":� 2� !:		3� +� @� Y� � � 4� !� � ":� :-� #� $:::� �� � -:
6	� -:25� +� �25� +� �25� +� �25� +� �
d2� /�6
d� 0S:6
�� "� Y� � 
2� � ":���ܻ Y� � � 4� !� � ":-� #� $:::���,� ,� %-� -� &� :,� ,� %-� -� &��  ��  ���    B   � 5   �  �  �  �  �  �  � " � % � 0 � 3 � _ � f � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � �# �& �4 �@DOhn����	���������� C   � G ' c O  � � m I 
 � � | O  � � } I 4 l n O  � q R 	 ,w T O  � U R  � o R  "� p R  %~ r O   � V R    � W R  � X Y  � Z [  \   � � % 	 ] ] ^ _  ] ] ]  � � 
 ] ] ^ _ ] ] ] ]  � ' s s�  � &� 1 	 ] ] ^ _ ] ] ]  �   ] ] ^ _  J `� 	  ] ] ^ _            `  �   ] ] ^ _   J     K  ~ @  A  	�     	�� 6Y�
YOYOYOYOSY�
YOYOYOYOSY�
YOYOYOYOSY�
YOYOYOYOSY�
YOYOYOYOSY�
YOYOYOYOSY�
YOYOYOYOS�  � 6Y�
YOYOYOYOYOYOYOSY�
YOYOYOYOYOYOYOSY�
YOYOYOYOYOYOYOS� )� 6Y�
YOYOYOYOYOYOYOSY�
YOYOYOYOYOYOYOSY�
YOYOYOYOYOYOYOSY�
YOYOYOYOYOYOYOS� ,� 6Y�
YOYOYOYOYOYOYOYOYOY	OY
OSY�
YOYOYOYOYOYOYOYOYOY	OY
OSY�
YOYOYOYOYOYOYOYOYOY	OY
OSY�
YOYOYOYOYOYOYOYOYOY	OY
OSY�
YOYOYOYOYOYOYOYOYOY	OY
OSY�
YOYOYOYOYOYOYOYOYOY	OY
OSY�
YOYOYOYOYOYOYOYOYOY	OY
OSY�
YOYOYOYOYOYOYOYOYOY	OY
OSY�
YOYOYOYOYOYOYOYOYOY	OY
OSY	�
YOYOYOYOYOYOYOYOYOY	OY
OSY
�
YOYOYOYOYOYOYOYOYOY	OY
OSY�
YOYOYOYOYOYOYOYOYOY	OY
OSY�
YOYOYOYOYOYOYOYOYOY	OY
OSY�
YOYOYOYOYOYOYOYOYOY	OY
OSY�
YOYOYOYOYOYOYOYOYOY	OY
OS� 1� 6Y�
YOYOYOYOYOYOYOYOYOY	OY
OYOYOYOYOSY�
YOYOYOYOYOYOYOYOYOY	OY
OYOYOYOYOSY�
YOYOYOYOYOYOYOYOYOY	OY
OYOYOYOYOSY�
YOYOYOYOYOYOYOYOYOY	OY
OYOYOYOYOS� 2� 6Y�
YOYOYOYOYOYOYOYOYOY	OY
OYOYOYOYOSY�
YOYOYOYOYOYOYOYOYOY	OY
OYOYOYOYOSY�
YOYOYOYOYOYOYOYOYOY	OY
OYOYOYOYOSY�
YOYOYOYOYOYOYOYOYOY	OY
OYOYOYOYOSY�
YOYOYOYOYOYOYOYOYOY	OY
OYOYOYOYOSY�
YOYOYOYOYOYOYOYOYOY	OY
OYOYOYOYOSY�
YOYOYOYOYOYOYOYOYOY	OY
OYOYOYOYOSY�
YOYOYOYOYOYOYOYOYOY	OY
OYOYOYOYOSY�
YOYOYOYOYOYOYOYOYOY	OY
OYOYOYOYOSY	�
YOYOYOYOYOYOYOYOYOY	OY
OYOYOYOYOSY
�
YOYOYOYOYOYOYOYOYOY	OY
OYOYOYOYOS� 4�    B        �  �  =       �