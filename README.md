# UDP

Esse projeto foi realizado na disciplina de Sistemas distribuídos do professor Evandro Luquini
Ele é uma versão modificada do projeto: https://github.com/brunoapimentel/chat

Anotações sobre dúvidas do projeto:
InputHandler.java:
Linha 28 - Verifica se o usuário não existe, mas deixa enviar a mensagem?
Linha 35 - Modificamos a classe para que encontre usuários não somente por IP, e também por seu nickname

Robot.java
Robo deixou de ser usado

Projeto em si:
Não informa aos usuário a desconexão do usuário, permitindo assim enviar mensagem a pessoas offline

Como fazer a implementação em qualquer outra interface java:
Implementar o InputHandler para que assim seja possível enviar mensagens as pessoas

Criar um Listener e informar como ele deve agir quando receber um evento (Esse evento tem um método para recuperação de mensagem getMessage())

Crie e inicie a thread server
Crie e inicie a thread client

Para enviar uma mensagem é necessário chamar o método readInput(String) do InputHandler, a String enviada tem que estar no formato: <IP ou Nickname do alvo>/<Mensagem para enviar>
Exemplo:
"Nicholas/Essa mensagem é um teste";
"Wesley/Vai safadão";

Para mostrar uma mensagem é necessário pegar a instância de Dispatcher e executar o método dispatchEvent(new MessageEvent(String mensagem));
Exemplo:
Dispatcher.getInstance().dispatchEvent(new MessageEvent("Olá mundo"));
