# IoC

원래 의존성에 대한 제어권은 자기자신이 들고 있다. 아래처럼!

```bash
class OwnerController {
   private OwnerRepository repository = new OwnerRepository(); 
}
```

IoC는 Inversion Of Control! 알아서 누군가가 주입해주겠거니~ 하면서 만들어주는 것이야! 

```bash
class OwnerController {
   private OwnerRepository repo; 
   public OwnerController(OwnerRepository repo) {
      this.repo = repo; 
   }
   // repo 사용
}
```
OwnerController 밖에 있는 OwnerControllerTest가 OwnerRepository라는 의존성을 OwnerController에 주입한다. 이게 바로 의존성 주입(DI)! 

```bash
class OwnerControllerTest {
   @Test
   public void create() {
      OwnerRepository repo = new OwnerRepository();
      OwnerController controller = new OwnerController(repo); 
   }
}
```

