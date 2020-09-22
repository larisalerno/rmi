all:	Client.class \
			Jogador.class \
			IJogador.class \
			IJogo.class \
			AppServer.class \
			Jogo.class \
			PokerThread.class \
			GameUtils.class \
			RandomIDGenerator.class

Client.class:	client/Client.java
			@javac client/Client.java

Jogador.class: client/Jogador.java
			@javac client/Jogador.java

IJogador.class:	interfaces/IJogador.java
			@javac interfaces/IJogador.java

IJogo.class:	interfaces/IJogo.java
			@javac interfaces/IJogo.java

AppServer.class: server/AppServer.java
			@javac server/AppServer.java

Jogo.class: server/Jogo.java
			@javac server/Jogo.java

PokerThread.class: server/PokerThread.java
			@javac server/PokerThread.java

GameUtils.class: utils/GameUtils.java
			@javac utils/GameUtils.java

RandomIDGenerator.class: utils/RandomIDGenerator.java
			@javac utils/RandomIDGenerator.java

clean:
			@rm -f client/*.class *~ \
			@rm -f interfaces/*.class *~ \
			@rm -f server/*.class *~ \
			@rm -f utils/*.class *~ \
