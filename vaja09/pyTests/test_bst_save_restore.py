import pexpect

def test_bst_save_restore():
    baza = pexpect.pexpect()

    try:

        baza.expect("Enter command: ")

        baza.send("use bst")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add 1")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add 2")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add 3")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("print")
        baza.expect("\t\t3")
        baza.expect("\t2")
        baza.expect("1")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("save test.bin")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("reset")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("print")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("size")
        baza.expect("0")
        baza.expect("Enter command: ")

        baza.send("restore test.bin")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("print")
        baza.expect("\t3")
        baza.expect("2")
        baza.expect("\t1")
        baza.expect("OK")
        baza.expect("Enter command: ")

        print("PASSED\ttest_bst_save_restore")

    except:
        print("FAILED\ttest_bst_save_restore")

    finally:
        baza.kill()


if __name__ == "__main__":
    test_bst_save_restore()

