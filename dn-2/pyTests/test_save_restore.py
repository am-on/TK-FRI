import pexpect


def test_save_restore():
    baza = pexpect.pexpect()

    try:
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("631")

        baza.expect("First name: ")
        baza.send("ime")

        baza.expect("Last name: ")
        baza.send("priimek")

        baza.expect("Avg. grade: ")
        baza.send("8")

        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 1")

        # second
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("632")

        baza.expect("First name: ")
        baza.send("ime1")

        baza.expect("Last name: ")
        baza.send("priimek1")

        baza.expect("Avg. grade: ")
        baza.send("8")

        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 2")

        # third
        baza.send("add")

        baza.expect("Student ID: ")
        baza.send("633")

        baza.expect("First name: ")
        baza.send("ime2")

        baza.expect("Last name: ")
        baza.send("priimek2")

        baza.expect("Avg. grade: ")
        baza.send("8")

        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 3")

        baza.send("save baza")
        baza.expect("OK")

        baza.send("reset")
        baza.expect("Are you sure (y|n): ")
        baza.send("y")
        baza.expect("OK")

        baza.send("count")
        baza.expect("No. of students: 0")

        baza.send("restore baza")
        baza.expect("OK")

        baza.send("print")
        baza.expect("No. of students: 3")
        baza.expect("\t631 | priimek, ime | 8")
        baza.expect("\t632 | priimek1, ime1 | 8")
        baza.expect("\t633 | priimek2, ime2 | 8")      


        print("PASSED\ttest_save_restore")

    except:
        print("FAILED\ttest_save_restore")

    finally:
        baza.kill()


if __name__ == "__main__":
    test_save_restore()
