# sdk-contract-vm-test-stub
In the development of nuls smart contract, it depends on the jar package named "sdk-contract-vm". The interface of this program is consistent with that of sdk-contract-vm.jar and simulates the business logic. The introduction of this simulated jar package in the process of smart contract development can enable contract development and debugging in IDEA.

## How to build
### condition
- JDK1.8 
- Maven 3.5 and above
### Get the source
     git clone https://github.com/CCC-NULS/sdk-contract-vm-test-stub.git

### Build source
    mvn clean install

## How to work
Add the following dependency information to pom.xml of smart contract project and remove the dependency on “sdk-contract-vm”.

         <dependency>
           <groupId>io.nuls.sdk.stub</groupId>
           <artifactId>sdk-contract-vm-test-stub</artifactId>
           <version>1.0-SNAPSHOT</version>
         </dependency>

## Write test cases
Test cases can be divided into local simulation data test and call to nuls-api interface test. After the parameters are set by the method provided by mockenvutil class, the operation of smart contract publishing and calling can be realized through the initialization and calling of common objects.

### Local simulation data test
Deployment smart contract example:

       MockEnvUtil.putMockCallReturnValue("tNULSeBaN2SH39sV44u8MjNehTmrkG8ZMQfyFV","name","testPocm");
       MockEnvUtil.putMockCallReturnValue("tNULSeBaN2SH39sV44u8MjNehTmrkG8ZMQfyFV","decimals","3");
       MockEnvUtil.putMockCallReturnValue("tNULSeBaN2SH39sV44u8MjNehTmrkG8ZMQfyFV","symbol","TEST");
        Pocm pocmtest=new Pocm("tNULSeBaN2SH39sV44u8MjNehTmrkG8ZMQfyFV",new BigDecimal("6"),3,new BigInteger("2"),3,false,3,"123","0","300");
        String address= ContractOperUtil.createContractForLocal(pocmtest,"tNULSeBaMu38g1vnJsSZUCwTDU9GsE5TVNUtpD",25);
        System.out.println("contract address: "+address);

Call smart contract example:

        MockEnvUtil.putMockCallReturnValue("tNULSeBaN2SH39sV44u8MjNehTmrkG8ZMQfyFV","balanceOf","30000000");
        String contractAddress="tNULSeBaN3qcFmtijoQbFKZkVivq2vspbjQuUf";
        Object result= ContractOperUtil.callContractFormLocal(Pocm.class,contractAddress,"depositForOwn",null,"tNULSeBaMu38g1vnJsSZUCwTDU9GsE5TVNUtpD",new BigInteger("400000000"),25);
        System.out.println("-----call result-----"+result);


### Call NULS-API interface test
Deployment smart contract example:

        try {
            MockEnvUtil.initMockEnv(false,"http://127.0.0.1:18004");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Pocm pocmtest=new Pocm("tNULSeBaN2SH39sV44u8MjNehTmrkG8ZMQfyFV",new BigDecimal("6"),3,new BigInteger("2"),3,false,3,"123","0","300");
        String address= ContractOperUtil.createContractForLocal(pocmtest,"tNULSeBaMu38g1vnJsSZUCwTDU9GsE5TVNUtpD",25);
        System.out.println("contract address: "+address);

Call smart contract example:

        try {
            MockEnvUtil.initMockEnv(false,"http://127.0.0.1:18004");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String contractAddress="tNULSeBaN3qcFmtijoQbFKZkVivq2vspbjQuUf";
        Object result= ContractOperUtil.callContractFormLocal(Pocm.class,contractAddress,"depositForOwn",null,"tNULSeBaMu38g1vnJsSZUCwTDU9GsE5TVNUtpD",new BigInteger("400000000"),25);
        System.out.println("-----call result-----"+result);
