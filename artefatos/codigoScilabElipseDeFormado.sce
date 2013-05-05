nivel=10;
x=0:0.001:6.28;
a=rand(1:size(x,2));
b=fft(a);
c=[b(1:nivel) zeros(1,size(x,2)-nivel)];
d=ifft(c);
plot(sin(x),cos(x)+(d*nivel))
plot(sin(x)*2.3,cos(x)+(d*nivel),"r")
