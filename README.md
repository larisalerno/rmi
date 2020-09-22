#

* [X] Ao registrar um jogador no servidor, o jogador recebe um id único inteiro
* [X] O servidor deve ser iniciado com um parametro definindo o numero de jogadores
* [X] Quando todos os jogadores houverem realizado o registro, o servidor deverá invocar o método **inicia()**
* [X] Cada jogador deve realizar M jogadas - método **joga()** - e após finalizar - método **encerra()**
    * [X] Cada jogada realizada deve ocorrer entre intervalos de 500ms a 1500ms (aleatório)
* [ ] A cada jogada, existe uma probabilidade de 1% do servidor finalizar o jogador - método **encerrar()**
* [ ] Testar com 3 instâncias de VM e 5 terminais
* [X] O servidor deve cutucar cada jogador a cada 3 segundos, para verificar se ainda esta ativo. Caso não estiver, 
finaliza o jogador.
