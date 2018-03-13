# Bitcoin Zero Full-Node Wallet

Forked from https://github.com/ZencashOfficial/zencash-swing-wallet-ui

## Building, Installing, and Running the Wallet

*This is a full-node wallet, a GUI to BCZ full node. Without the full node itself, this wallet will NOT work. Guides for running a BCZ full node can be found at https://github.com/bitcoinzero/bitcoinzero. *

### 1. Operating System and Tools

   You will need Git, Java (JDK7 or later), and Ant.

   **Ubuntu Linux -**
   ```
   sudo apt-get install git default-jdk ant
   ```
   **RedHat/CentOS/Fedora Linux -**
   ```
   sudo yum install java-1.8.0-openjdk git ant
   ```
   The name of the JDK package (`java-1.8.0-openjdk`) may vary depending on the Linux system, so look around if name `java-1.8.0-openjdk` can't be found or doesn't work.

   Commands `git`, `java`, `javac`, `ant` need to be runnable from command line
   before proceeding with build.

### 2. Building from Source Code

   First, clone this Git repository:
   ```
   git clone https://github.com/bitcoinzero/bitcoinzero-full-node-wallet
   ```
   Enter:
   ```
   cd bitcoinzero-full-node-wallet/
   ```
   Build:
   ```
   ant -buildfile ./src/build/build.xml
   ```
   This may take a few seconds. When it finishes, you will now see `build/jars/BitcoinZeroSwingWallet.jar`.

   You need to make this file executable:
   ```
   chmod u+x ./build/jars/BitcoinZeroSwingWallet.jar
   ```
   At this point the build process is finished! The final product is the GUI Wallet Java JAR: `build/jars/BitcoinZeroSwingWallet.jar`

### 3. Binding BCZ full node

Once you've built Bitcoin Zero from source code https://github.com/bitcoinzero/bitcoinzero, `zcashd` and `zcash-cli` will appear in `~/bitcoinzero/src`.

You will need to copy them beside the jar, and rename them to `bczd` and `bcz-cli`:

```
cp ~/bitcoinzero/src/zcashd build/jars/bczd
cp ~/bitcoinzero/src/zcash-cli build/jars/bcz-cli
```

You can now run the Desktop GUI Wallet:

```
java -jar build/jars/BitcoinZeroSwingWallet.jar
```

Or just double-click it!


If you are using Ubuntu or another Linux, you may need to
right-click `BitcoinZeroSwingWallet.jar` file and choose "Open with OpenJDK 8 Runtime".

## Notes from ZENCash - Known Issues and Limitations

1. **Issue:** The Zclassic Desktop GUI Wallet is not compatible with applications that modify the ZCL `wallet.dat` file. The wallet should not be used
with such applications on the same PC. For instance some distributed exchange applications are known to create watch-only addresses in the
`wallet.dat` file that cause the GUI wallet to display a wrong balance and/or display addresses that do not belong to the wallet.
1. **Limitation:** if two users exchange text messages via the messaging UI TAB and one of them has a system clock, substantially running slow or fast by more than 1 minute, it is possible that this user will see text messages appearing out of order.
1. **Limitation:** if a messaging identity has been created (happens on first click on the messaging UI tab), then replacing the `wallet.dat` or changing the node configuration between mainnet and testnet will make the identity invalid. This will result in a wallet update error. To remove the error the directory `~/.ZclassicSwingWallet/messaging` may be manually renamed or deleted (when the wallet is stopped). **CAUTION: all messaging history will be lost in this case!**
1. **Limitation:** Wallet encryption has been temporarily disabled in Zclassic due to stability problems. A corresponding issue
[#1552](https://github.com/zcash/zcash/issues/1552) has been opened by the Zcash developers. Correspondingly,
wallet encryption has been temporarily disabled in the Zclassic Desktop GUI Wallet.
The latter needs to be disabled.
1. **Limitation:** The list of transactions does not show all outgoing ones (specifically outgoing Z address
transactions). A corresponding issue [#1438](https://github.com/zcash/zcash/issues/1438) has been opened
for the Zcash developers.
1. **Limitation:** The CPU percentage shown to be taken by zcld on Linux is the average for the entire lifetime
of the process. This is not very useful. This will be improved in future versions.
1. **Limitation:** When using a natively compiled wallet version (e.g. `ZclassicSwingWallet.exe` for Windows) on a
very high resolution monitor with a specifically configured DPI scaling (enlargement) factor to make GUI
elements look larger, the GUI elements of the wallet actually do not scale as expected. To correct this on
Windows you need to right-click on `ZclassicSwingWallet.exe` and choose option:
```
Properties >> Compatibility >> Override high DPI scaling behavior >> Scaling performed by: (Application)
```


### License
Originally forked from the [ZENCash Swing Wallet](https://github.com/ZencashOfficial/zencash-swing-wallet-ui).

### Disclaimer

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
