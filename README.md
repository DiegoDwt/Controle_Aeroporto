# Controle_Aeroporto
Trabalho da Disciplina Estrutura de Dados

Estruturas de Dados– Profº Daniel Gomes Soares

Ciência da Computação 2023

O objetivo deste trabalho é simular os padrões de aterrissagem e decolagem em um
aeroporto. Suponha um aeroporto que possui duas pistas, numeradas como 1 e 2. Existem 2
“prateleiras” de espera para aterrissagem, uma para cada pista. Aeronaves que se aproximam
do aeroporto devem integrar-se a uma das prateleiras (filas) de espera, sendo que essas filas
devem procurar manter o mesmo tamanho. Assim que um avião entra em uma fila de
aterrissagem, ele recebe um número de identificação ID e outro número inteiro que indica a
quantidade de unidades de tempo que o avião pode permanecer na fila antes que ele tenha de
descer (do contrário, seu combustível termina e ele cai).
Existem também filas para decolagem, uma para cada pista. Os aviões que chegam nessas filas
também recebem uma identificação ID.
A cada unidade de tempo, de zero a duas aeronaves podem chegar às filas de decolagem, e de
zero a duas aeronaves podem chegar às prateleiras. A cada unidade de tempo cada pista pode
ser usada para um pouso ou uma decolagem. Se alguma(s) aeronaves estiverem com falta de
combustível ela(s) terão prioridade para aterrissar. A cada unidade de tempo no máximo dois
aviões poderão estar nesta desagradável situação. Utilize inteiros pares (ímpares) sucessivos
para o ID dos aviões chegando às filas de decolagem (aterrissagem). A cada unidade de tempo,
assuma que os aviões entrem nas filas antes que aterrissagens ou decolagens ocorram. Tente
projetar um algoritmo que não permita o crescimento excessivo das filas de aterrissagem ou
decolagem. Coloque os aviões que chegam no final das filas, que não devem ser reordenadas.
A saída do programa deverá indicar o que ocorre a cada unidade de tempo. Periodicamente
mostre:
a) O conteúdo de cada fila;
b) O tempo médio de espera para decolagem;
c) O tempo médio de espera para aterrissagem;
d) O número de aviões que aterrissam de reserva de combustível.
Os itens b e c devem ser calculados para os aviões que já decolaram ou pousaram,
respectivamente. A saída do programa deve ser autoexplicativa e fácil de entender.
A entrada pode ser criada manualmente, ou utilizar um gerador de números aleatórios. Para
cada unidade de tempo, a entrada deve ter as seguintes informações:
a) Número de aviões (zero a dois) chegando às filas de aterrissagem com respectivas
reservas de combustível (de 1 a 20 unidades de tempo);
b) Número de aviões (zero a dois) chegando às filas de decolagem.
